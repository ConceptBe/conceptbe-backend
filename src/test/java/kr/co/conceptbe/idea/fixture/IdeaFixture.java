package kr.co.conceptbe.idea.fixture;

import java.util.List;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.vo.CooperationWay;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.purpose.domain.Purpose;
import kr.co.conceptbe.region.domain.Region;
import kr.co.conceptbe.skill.domain.SkillCategory;

public class IdeaFixture {

    public static Idea createIdea(
            Region region,
            List<Branch> branch,
            List<Purpose> purpose,
            List<SkillCategory> skillCategory,
            Member member
    ) {
        return Idea.of(
                "title",
                "introduce".repeat(2),
                "상관없음",
                region,
                member,
                branch,
                purpose,
                skillCategory
        );
    }

    public static Idea createIdeaByCooperationWay(
        String cooperationWay,
        Region region,
        List<Branch> branch,
        List<Purpose> purpose,
        List<SkillCategory> skillCategory,
        Member member
    ) {
        return Idea.of(
            "title",
            "introduce".repeat(2),
            cooperationWay,
            region,
            member,
            branch,
            purpose,
            skillCategory
        );
    }

}
