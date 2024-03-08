package kr.co.conceptbe.idea.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.branch.domain.persistense.BranchRepository;
import kr.co.conceptbe.idea.application.response.FindIdeaWriteResponse;
import kr.co.conceptbe.idea.application.response.SkillCategoryResponse;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.persistence.IdeaRepository;
import kr.co.conceptbe.idea.fixture.IdeaFixture;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.domain.OauthId;
import kr.co.conceptbe.member.domain.OauthServerType;
import kr.co.conceptbe.member.persistence.MemberRepository;
import kr.co.conceptbe.purpose.domain.Purpose;
import kr.co.conceptbe.purpose.domain.persistence.PurposeRepository;
import kr.co.conceptbe.region.domain.Region;
import kr.co.conceptbe.region.domain.presentation.RegionRepository;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.skill.domain.SkillCategoryRepository;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class IdeaServiceTest {

    private static final String VALID_TITLE = "제목";
    private static final String INVALID_TITLE = "한";
    private static final String VALID_INTRODUCE = "소개".repeat(5);
    private static final String INVALID_INTRODUCE = "10글자미만";
    private static final String VALID_COOPERATION = "온라인";
    private static final String INVALID_COOPERATION = "협업선택지아님";
    private static final int VALID_BRANCH_COUNT = 1;
    private static final int INVALID_BRANCH_COUNT = 0;
    private static final int VALID_PURPOSE_COUNT = 1;
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

    @ParameterizedTest(name = "유효하지 않은 {0}(이)가 들어오는 경우 게시글 작성에 실패한다.")
    @MethodSource("invalidWriteIdeaRequest")
    void 게시글을_작성한다() {
        // given

        // when

        // then
    }

    @Test
    void 게시글을_update_한다() {
        // given
        Idea savedIdea = ideaRepository.save(createValidIdea());
        List<Branch> branches = getBranchesByCount(VALID_BRANCH_COUNT);
        List<Purpose> purposes = getPurposesByCount(VALID_PURPOSE_COUNT);
        List<SkillCategory> skillCategories = getSkillCategoriesByCount(VALID_SKILL_COUNT);

        // when
        IdeaService.updateIdea(
            savedIdea.getId(),
            VALID_TITLE,
            VALID_INTRODUCE,
            VALID_COOPERATION,
            branches,
            purposes,
            skillCategories
        );

        // then
        assertAll(
            () -> assertThat(savedIdea.getTitle()).isEqualTo(VALID_TITLE),
            () -> assertThat(savedIdea.getIntroduce()).isEqualTo(VALID_INTRODUCE),
            () -> assertThat(savedIdea.getCooperationWay()).isEqualTo(VALID_COOPERATION),
            () -> assertThat(savedIdea.getBranches()).hasSize(VALID_BRANCH_COUNT),
            () -> assertThat(savedIdea.getPurposes()).hasSize(VALID_PURPOSE_COUNT),
            () -> assertThat(savedIdea.getSkillCategories()).hasSize(VALID_SKILL_COUNT)
        );
    }

    @ParameterizedTest(name = "유효하지 않은 {0}(이)가 들어오는 경우 게시글 수정에 실패한다.")
    @MethodSource("invalidWriteIdeaRequest")
    void 유효하지_않은_내용으로_게시글을_수정하는_경우_게시글_작성에_실패한다(
        String testName,
        String title,
        String introduce,
        String cooperation,
        long branchCount,
        long purposeCount,
        long skillCount
    ) {
        // given
        Idea savedIdea = ideaRepository.save(createValidIdea());
        List<Branch> branches = getBranchesByCount(branchCount);
        List<Purpose> purposes = getPurposesByCount(purposeCount);
        List<SkillCategory> skillCategories = getSkillCategoriesByCount(skillCount);

        // when
        ThrowingCallable throwingCallable = () -> IdeaService.updateIdea(
            savedIdea.getId(),
            title,
            introduce,
            cooperation,
            branches,
            purposes,
            skillCategories
        );

        // then
        assertThatThrownBy(throwingCallable)
            .isInstanceOf(IllegalArgumentException.class);
    }

    private Idea createValidIdea() {
        Branch branch = branchRepository.save(Branch.from("branch"));
        Purpose purpose = purposeRepository.save(Purpose.from("purpose"));
        SkillCategory skillCategory = skillCategoryRepository.save(new SkillCategory("skill"));
        Region region = regionRepository.save(Region.from("BUSAN"));
        Member member = memberRepository.save(new Member(
            new OauthId("1", OauthServerType.KAKAO),
            "nickname",
            "profileImageUrl",
            "email",
            "introduce",
            "전국",
            kr.co.conceptbe.member.domain.Region.BUSAN
        ));
        return IdeaFixture.createIdea(
            region, List.of(branch), List.of(purpose), List.of(skillCategory), member
        );
    }

    private List<Branch> getBranchesByCount(long count) {
        List<Branch> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            branchRepository.save(Branch.from("branches" + i));
        }
        return results;
    }

    private List<Purpose> getPurposesByCount(long count) {
        List<Purpose> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            purposeRepository.save(Purpose.from("purposes" + i));
        }
        return results;
    }

    private List<SkillCategory> getSkillCategoriesByCount(long count) {
        List<SkillCategory> results = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            skillCategoryRepository.save(new SkillCategory("skillCategories" + i));
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
