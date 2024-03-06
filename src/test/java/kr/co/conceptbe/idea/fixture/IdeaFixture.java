package kr.co.conceptbe.idea.fixture;

import java.util.List;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.purpose.domain.Purpose;
import kr.co.conceptbe.region.domain.Region;
import kr.co.conceptbe.skill.domain.SkillCategory;

public class IdeaFixture {

    public static Idea createIdea(
            Region region,
            Branch branch,
            Purpose purpose,
            SkillCategory skillCategory,
            Member member
    ) {
        return Idea.of(
                "title",
                "introduce".repeat(2),
                "상관없음",
                region,
                member,
                List.of(branch),
                List.of(purpose),
                List.of(skillCategory)
        );
    }

}
