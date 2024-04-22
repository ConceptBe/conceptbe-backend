package kr.co.conceptbe.idea.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import kr.co.conceptbe.auth.presentation.dto.AuthCredentials;
import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.branch.domain.persistense.BranchRepository;
import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.comment.CommentLike;
import kr.co.conceptbe.idea.application.request.FilteringRequest;
import kr.co.conceptbe.idea.application.request.IdeaRequest;
import kr.co.conceptbe.idea.application.request.IdeaUpdateRequest;
import kr.co.conceptbe.idea.application.response.FindIdeaWriteResponse;
import kr.co.conceptbe.idea.application.response.IdeaResponse;
import kr.co.conceptbe.idea.application.response.SkillCategoryResponse;
import kr.co.conceptbe.idea.domain.Hit;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaLike;
import kr.co.conceptbe.idea.domain.persistence.HitRepository;
import kr.co.conceptbe.idea.domain.persistence.IdeaRepository;
import kr.co.conceptbe.idea.dto.IdeaDetailResponse;
import kr.co.conceptbe.idea.fixture.IdeaFixture;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.domain.OauthId;
import kr.co.conceptbe.member.domain.OauthServerType;
import kr.co.conceptbe.member.fixture.MemberFixture;
import kr.co.conceptbe.member.persistence.MemberRepository;
import kr.co.conceptbe.purpose.domain.Purpose;
import kr.co.conceptbe.purpose.domain.persistence.PurposeRepository;
import kr.co.conceptbe.region.domain.Region;
import kr.co.conceptbe.region.domain.presentation.RegionRepository;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.skill.domain.SkillCategoryRepository;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class IdeaServiceTest {

    private static final String VALID_TITLE = "제목";
    private static final String INVALID_TITLE = "21글자야야야".repeat(3);
    private static final String VALID_INTRODUCE = "소개".repeat(5);
    private static final String INVALID_INTRODUCE = "10글자미만";
    private static final String VALID_COOPERATION = "온라인";
    private static final String INVALID_COOPERATION = "협업선택지아님";
    private static final int VALID_BRANCH_COUNT = 2;
    private static final int INVALID_BRANCH_COUNT = 0;
    private static final int VALID_PURPOSE_COUNT = 2;
    private static final int INVALID_PURPOSE_COUNT = 0;
    private static final int VALID_SKILL_COUNT = 8;
    private static final int INVALID_SKILL_COUNT = 11;

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;
    @Autowired
    private IdeaService ideaService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private PurposeRepository purposeRepository;
    @Autowired
    private IdeaRepository ideaRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private HitRepository hitRepository;

    @Test
    void 게시글을_상세_조회했을_때_조회_기록이_남는다() {
        //given
        Region region = regionRepository.save(Region.from("BUSAN"));
        SkillCategory java = skillCategoryRepository.save(new SkillCategory("Java"));
        Member member = memberRepository.save(MemberFixture.createMemberByMainSkill(java));
        Idea savedIdea = ideaRepository.save(
            Idea.of(
                VALID_TITLE,
                VALID_INTRODUCE,
                VALID_COOPERATION,
                region,
                member,
                getBranchesByCount(VALID_BRANCH_COUNT),
                getPurposesByCount(VALID_PURPOSE_COUNT),
                getSkillCategoriesByCount(VALID_SKILL_COUNT)
            )
        );

        //when
        ideaService.getDetailIdeaResponse(member.getId(),
            savedIdea.getId());
        Optional<Hit> firstByMemberAndIdeaOrderByCreatedAtDesc = hitRepository.findFirstByMemberAndIdeaOrderByCreatedAtDesc(
            member, savedIdea);

        //then
        assertThat(firstByMemberAndIdeaOrderByCreatedAtDesc).isPresent();
    }

    @Test
    void 게시글을_조회한다() {
        //given
        Region region = regionRepository.save(Region.from("BUSAN"));
        SkillCategory java = skillCategoryRepository.save(new SkillCategory("Java"));
        Member member = memberRepository.save(MemberFixture.createMemberByMainSkill(java));
        Idea save = ideaRepository.save(
            Idea.of(
                VALID_TITLE,
                VALID_INTRODUCE,
                VALID_COOPERATION,
                region,
                member,
                getBranchesByCount(VALID_BRANCH_COUNT),
                getPurposesByCount(VALID_PURPOSE_COUNT),
                getSkillCategoriesByCount(VALID_SKILL_COUNT)
            )
        );
        AuthCredentials authCredentials = new AuthCredentials(member.getId());
        FilteringRequest nonFilter = new FilteringRequest(null, null, null, null, null);
        PageRequest pageRequest = PageRequest.of(0, 5);

        //when
        List<IdeaResponse> ideaResponses = ideaService.findAll(authCredentials, nonFilter,
            pageRequest);

        //then
        assertAll(
            () -> assertThat(ideaResponses).hasSize(1),
            () -> assertThat(ideaResponses.get(0).id()).isEqualTo(save.getId()),
            () -> assertThat(ideaResponses.get(0).title()).isEqualTo(save.getTitle()),
            () -> assertThat(ideaResponses.get(0).introduce()).isEqualTo(save.getIntroduce()),
            () -> assertThat(ideaResponses.get(0).memberResponse().mainSkill()).isEqualTo(
                member.getMainSkill().getName()),
            () -> assertThat(ideaResponses.get(0).branches()).hasSize(2),
            () -> assertThat(ideaResponses.get(0).skillCategories()).hasSize(8)
        );
    }


    @Test
    void Idea_작성_Filtering_에_필요한_Skill_Categories_를_반환한다() {
        // given
        SkillCategory develop = skillCategoryRepository.save(new SkillCategory("개발"));
        SkillCategory backend = skillCategoryRepository.save(new SkillCategory(develop, "BE"));
        SkillCategory frontend = skillCategoryRepository.save(new SkillCategory(develop, "FE"));

        // when
        FindIdeaWriteResponse response = ideaService.getFindIdeaWriteResponse();
        SkillCategoryResponse skillCategoryResponse = response.skillCategoryResponses().get(0);

        // then
        assertAll(
            () -> assertThat(skillCategoryResponse.id()).isEqualTo(develop.getId()),
            () -> assertThat(skillCategoryResponse.name()).isEqualTo(develop.getName()),
            () -> assertThat(skillCategoryResponse.skillResponses()).hasSize(2),
            () -> assertThat(skillCategoryResponse.skillResponses().get(0).id()).isEqualTo(
                backend.getId()),
            () -> assertThat(skillCategoryResponse.skillResponses().get(0).name()).isEqualTo(
                backend.getName()),
            () -> assertThat(skillCategoryResponse.skillResponses().get(1).id()).isEqualTo(
                frontend.getId()),
            () -> assertThat(skillCategoryResponse.skillResponses().get(1).name()).isEqualTo(
                frontend.getName())
        );
    }

    @Test
    void 게시글을_작성한다() {
        // given
        Region region = regionRepository.save(Region.from("BUSAN"));
        Member member = memberRepository.save(MemberFixture.createMember());
        AuthCredentials authCredentials = new AuthCredentials(member.getId());
        IdeaRequest ideaRequest = new IdeaRequest(
            VALID_TITLE,
            VALID_INTRODUCE,
            VALID_COOPERATION,
            region.getId(),
            getBranchesByCount(VALID_BRANCH_COUNT).stream().map(Branch::getId).toList(),
            getPurposesByCount(VALID_PURPOSE_COUNT).stream().map(Purpose::getId).toList(),
            getSkillCategoriesByCount(VALID_SKILL_COUNT).stream().map(SkillCategory::getId).toList()
        );

        // when
        Long savedIdeaId = ideaService.save(authCredentials, ideaRequest);
        Idea savedIdea = ideaRepository.findById(savedIdeaId).get();

        // then
        assertAll(
            () -> assertThat(savedIdea.getTitle()).isEqualTo(VALID_TITLE),
            () -> assertThat(savedIdea.getIntroduce()).isEqualTo(VALID_INTRODUCE),
            () -> assertThat(savedIdea.getCooperationWay()).isEqualTo(VALID_COOPERATION),
            () -> assertThat(savedIdea.getRecruitmentPlace()).isEqualTo(region.getName()),
            () -> assertThat(savedIdea.getBranches()).hasSize(VALID_BRANCH_COUNT),
            () -> assertThat(savedIdea.getPurposes()).hasSize(VALID_PURPOSE_COUNT),
            () -> assertThat(savedIdea.getSkillCategories()).hasSize(VALID_SKILL_COUNT)
        );
    }

    @ParameterizedTest(name = "유효하지 않은 {0}으로 게시글을 작성하는 경우 게시글 작성에 실패한다.")
    @MethodSource("invalidWriteIdeaRequest")
    void 유효하지_않은_내용으로_게시글을_작성하는_경우_게시글_작성에_실패한다(
        String testName,
        String title,
        String introduce,
        String cooperation,
        long branchCount,
        long purposeCount,
        long skillCount
    ) {
        // given
        Region region = regionRepository.save(Region.from("BUSAN"));
        Member member = memberRepository.save(MemberFixture.createMember());
        AuthCredentials authCredentials = new AuthCredentials(member.getId());
        IdeaRequest ideaRequest = new IdeaRequest(
            title,
            introduce,
            cooperation,
            region.getId(),
            getBranchesByCount(branchCount).stream().map(Branch::getId).toList(),
            getPurposesByCount(purposeCount).stream().map(Purpose::getId).toList(),
            getSkillCategoriesByCount(skillCount).stream().map(SkillCategory::getId).toList()
        );

        // when
        ThrowingCallable throwingCallable = () -> ideaService.save(
            authCredentials,
            ideaRequest
        );

        // then
        assertThatThrownBy(throwingCallable)
            .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 게시글을_수정_한다() {
        // given
        Region region = regionRepository.save(Region.from("BUSAN"));
        Member member = memberRepository.save(MemberFixture.createMember());
        AuthCredentials authCredentials = new AuthCredentials(member.getId());
        Idea savedIdea = ideaRepository.save(createValidIdea(region, member));
        IdeaUpdateRequest ideaUpdateRequest = new IdeaUpdateRequest(
            VALID_TITLE,
            VALID_INTRODUCE,
            VALID_COOPERATION,
            region.getId(),
            getBranchesByCount(VALID_BRANCH_COUNT).stream().map(Branch::getId).toList(),
            getPurposesByCount(VALID_PURPOSE_COUNT).stream().map(Purpose::getId).toList(),
            getSkillCategoriesByCount(VALID_SKILL_COUNT).stream().map(SkillCategory::getId).toList()
        );

        // when
        ideaService.updateIdea(
            authCredentials,
            savedIdea.getId(),
            ideaUpdateRequest
        );
        Idea ideaByFindById = ideaRepository.findById(savedIdea.getId()).get();

        // then
        assertAll(
            () -> assertThat(ideaByFindById.getTitle()).isEqualTo(VALID_TITLE),
            () -> assertThat(ideaByFindById.getIntroduce()).isEqualTo(VALID_INTRODUCE),
            () -> assertThat(ideaByFindById.getCooperationWay()).isEqualTo(VALID_COOPERATION),
            () -> assertThat(ideaByFindById.getRecruitmentPlace()).isEqualTo(region.getName()),
            () -> assertThat(ideaByFindById.getBranches()).hasSize(VALID_BRANCH_COUNT),
            () -> assertThat(ideaByFindById.getPurposes()).hasSize(VALID_PURPOSE_COUNT),
            () -> assertThat(ideaByFindById.getSkillCategories()).hasSize(VALID_SKILL_COUNT)
        );
    }

    @ParameterizedTest(name = "유효하지 않은 {0}으로 게시글을 수정하는 경우 게시글 수정에 실패한다.")
    @MethodSource("invalidWriteIdeaRequest")
    void 유효하지_않은_내용으로_게시글을_수정하는_경우_게시글_수정에_실패한다(
        String testName,
        String title,
        String introduce,
        String cooperation,
        long branchCount,
        long purposeCount,
        long skillCount
    ) {
        // given
        Region region = regionRepository.save(Region.from("BUSAN"));
        Member member = memberRepository.save(MemberFixture.createMember());
        AuthCredentials authCredentials = new AuthCredentials(member.getId());
        Idea savedIdea = ideaRepository.save(createValidIdea(region, member));
        IdeaUpdateRequest ideaUpdateRequest = new IdeaUpdateRequest(
            title,
            introduce,
            cooperation,
            region.getId(),
            getBranchesByCount(branchCount).stream().map(Branch::getId).toList(),
            getPurposesByCount(purposeCount).stream().map(Purpose::getId).toList(),
            getSkillCategoriesByCount(skillCount).stream().map(SkillCategory::getId).toList()
        );

        // when
        ThrowingCallable throwingCallable = () -> ideaService.updateIdea(
            authCredentials,
            savedIdea.getId(),
            ideaUpdateRequest
        );

        // then
        assertThatThrownBy(throwingCallable)
            .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 게시글을_삭제한다() {
        // given
        Region region = regionRepository.save(Region.from("BUSAN"));
        Member member = memberRepository.save(MemberFixture.createMember());
        Idea idea = ideaRepository.save(createValidIdea(region, member));

        // when
        ideaService.deleteIdea(new AuthCredentials(member.getId()), idea.getId());
        Optional<Idea> deletedIdea = ideaRepository.findById(idea.getId());

        // then
        assertThat(deletedIdea).isEmpty();
    }

    @Test
    void 게시글과_연관된_데이터가_존재하더라도_삭제에_성공한다() {
        // given
        Region region = regionRepository.save(Region.from("BUSAN"));
        Member member = memberRepository.save(MemberFixture.createMember());
        Member notCreator = memberRepository.save(
            MemberFixture.createMemberByOauthId(new OauthId("notAuthor", OauthServerType.KAKAO)));
        Idea idea = ideaRepository.save(createValidIdea(region, member));
        Comment parentComment = Comment.createCommentAssociatedWithIdeaAndCreator("댓글", null, idea,
            member);
        CommentLike.createAssociatedWithMemberAndCreator(notCreator, parentComment);
        Comment.createCommentAssociatedWithIdeaAndCreator("대댓글", parentComment, idea, member);
        IdeaLike.createAssociatedWithIdeaAndMember(idea, member);
        Hit.ofIdeaAndMember(idea, member);
        Bookmark.createBookmarkAssociatedWithIdeaAndMember(idea, member);

        // when
        ideaService.deleteIdea(new AuthCredentials(member.getId()), idea.getId());
        Optional<Idea> deletedIdea = ideaRepository.findById(idea.getId());

        // then
        assertThat(deletedIdea).isEmpty();
    }

    @Test
    void 게시글_작성자가_아닌_유저가_게시글을_삭제하려는_경우_게시글_삭제에_실패한다() {
        // given
        Region region = regionRepository.save(Region.from("BUSAN"));
        Member member1 = memberRepository.save(
            MemberFixture.createMemberByOauthId(new OauthId("1", OauthServerType.KAKAO)));
        Member member2 = memberRepository.save(
            MemberFixture.createMemberByOauthId(new OauthId("2", OauthServerType.KAKAO)));
        Idea idea = ideaRepository.save(createValidIdea(region, member1));

        // when
        ThrowingCallable throwingCallable = () -> ideaService.deleteIdea(
            new AuthCredentials(member2.getId()),
            idea.getId()
        );

        // then
        assertThatThrownBy(throwingCallable)
            .isInstanceOf(RuntimeException.class);
    }

    private Idea createValidIdea(Region region, Member member) {
        Branch branch = branchRepository.save(Branch.from("branch"));
        Purpose purpose = purposeRepository.save(Purpose.from("purpose"));
        SkillCategory skillCategory = skillCategoryRepository.save(new SkillCategory("skill"));
        return IdeaFixture.createIdea(
            region, List.of(branch), List.of(purpose), List.of(skillCategory), member
        );
    }

    private List<Branch> getBranchesByCount(long count) {
        List<Branch> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            results.add(branchRepository.save(Branch.from("branches" + i)));
        }
        return results;
    }

    private List<Purpose> getPurposesByCount(long count) {
        List<Purpose> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            results.add(purposeRepository.save(Purpose.from("purposes" + i)));
        }
        return results;
    }

    private List<SkillCategory> getSkillCategoriesByCount(long count) {
        List<SkillCategory> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            results.add(skillCategoryRepository.save(new SkillCategory("skillCategories" + i)));
        }
        return results;
    }

    static Stream<Arguments> invalidWriteIdeaRequest() {
        return Stream.of(
            Arguments.of("제목", INVALID_TITLE, VALID_INTRODUCE, VALID_COOPERATION,
                VALID_BRANCH_COUNT, VALID_PURPOSE_COUNT, VALID_SKILL_COUNT),
            Arguments.of("소개", VALID_TITLE, INVALID_INTRODUCE, VALID_COOPERATION,
                VALID_BRANCH_COUNT, VALID_PURPOSE_COUNT, VALID_SKILL_COUNT),
            Arguments.of("협업방식", VALID_TITLE, VALID_INTRODUCE, INVALID_COOPERATION,
                VALID_BRANCH_COUNT, VALID_PURPOSE_COUNT, VALID_SKILL_COUNT),
            Arguments.of("분야", VALID_TITLE, VALID_INTRODUCE, VALID_COOPERATION,
                INVALID_BRANCH_COUNT, VALID_PURPOSE_COUNT, VALID_SKILL_COUNT),
            Arguments.of("목적", VALID_TITLE, VALID_INTRODUCE, VALID_COOPERATION,
                VALID_BRANCH_COUNT, INVALID_PURPOSE_COUNT, VALID_SKILL_COUNT),
            Arguments.of("기술", VALID_TITLE, VALID_INTRODUCE, VALID_COOPERATION,
                VALID_BRANCH_COUNT, VALID_PURPOSE_COUNT, INVALID_SKILL_COUNT)
        );
    }
}
