package kr.co.conceptbe.idea.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.branch.domain.persistense.BranchRepository;
import kr.co.conceptbe.idea.application.request.FilteringRequest;
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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class IdeaRepositoryTest {

    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private SkillCategoryRepository skillCategoryRepository;
    @Autowired
    private PurposeRepository purposeRepository;
    @Autowired
    private IdeaRepository ideaRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RegionRepository regionRepository;

    @Test
    void Query_DSL_을_사용한_Method_가_정상적으로_동작하는지_확인한다() {
        // given
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
        Idea idea = IdeaFixture.createIdea(region, branch, purpose, skillCategory, member);
        ideaRepository.save(idea);
        PageRequest pageable = PageRequest.of(0, 1);
        FilteringRequest filteringRequest = new FilteringRequest(
                List.of(branch.getId()),
                null,
                null,
                null,
                null
        );

        // when
        List<Idea> resultOfLikesDesc = ideaRepository.findAllByOrderByCreatedAtDesc(filteringRequest, pageable);
        List<Idea> resultOfAllIdeas = ideaRepository.findAllByOrderByLikesDesc(filteringRequest, pageable);

        // then
        assertThat(resultOfLikesDesc).hasSize(1);
        assertThat(resultOfAllIdeas).hasSize(1);
    }
}
