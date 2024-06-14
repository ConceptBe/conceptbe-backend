package kr.co.conceptbe.image.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.branch.domain.persistense.BranchRepository;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.persistence.IdeaRepository;
import kr.co.conceptbe.idea.fixture.IdeaFixture;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.exception.NotOwnerException;
import kr.co.conceptbe.member.fixture.MemberFixture;
import kr.co.conceptbe.member.persistence.MemberRepository;
import kr.co.conceptbe.purpose.domain.Purpose;
import kr.co.conceptbe.purpose.domain.persistence.PurposeRepository;
import kr.co.conceptbe.region.domain.Region;
import kr.co.conceptbe.region.domain.presentation.RegionRepository;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.skill.domain.SkillCategoryRepository;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IdeaValidatorTest {

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
    @Autowired
    private IdeaValidator ideaValidator;

    @Test
    void 게시글이_존재하는것을_확인한다() {
        //given
        Region region = regionRepository.save(Region.from("BUSAN"));
        Member member = memberRepository.save(MemberFixture.createMember(region));
        Branch branch = branchRepository.save(Branch.from("branch"));
        Purpose purpose = purposeRepository.save(Purpose.from("purpose"));
        SkillCategory skillCategory = skillCategoryRepository.save(new SkillCategory("skill"));
        Idea savedIdea = ideaRepository.save(IdeaFixture.createIdea(
            region, List.of(branch), List.of(purpose),
            List.of(skillCategory), member
        ));

        //when
        Executable executable = () -> ideaValidator.validateIdea(savedIdea.getId(), member.getId());

        //then
        assertDoesNotThrow(executable);
    }

    @Test
    void 게시글이_존재하지_않는_경우_예외가_발생한다() {
        //given when
        ThrowingCallable throwingCallable = () -> ideaValidator.validateIdea(
            10000L,
            Long.MAX_VALUE
        );

        //then
        assertThatThrownBy(throwingCallable)
            .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 게시글이_주인이_아닌_경우_예외가_발생한다() {
        //given
        Region region = regionRepository.save(Region.from("BUSAN"));
        Member member = memberRepository.save(MemberFixture.createMember(region));
        Branch branch = branchRepository.save(Branch.from("branch"));
        Purpose purpose = purposeRepository.save(Purpose.from("purpose"));
        SkillCategory skillCategory = skillCategoryRepository.save(new SkillCategory("skill"));
        Idea savedIdea = ideaRepository.save(IdeaFixture.createIdea(
            region, List.of(branch), List.of(purpose),
            List.of(skillCategory), member
        ));

        //when
        ThrowingCallable throwingCallable = () -> ideaValidator.validateIdea(savedIdea.getId(),
            Long.MAX_VALUE);

        //then
        assertThatThrownBy(throwingCallable)
            .isInstanceOf(NotOwnerException.class);
    }

}
