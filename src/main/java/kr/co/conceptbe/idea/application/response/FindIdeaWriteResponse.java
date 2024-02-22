package kr.co.conceptbe.idea.application.response;

import java.util.List;
import kr.co.conceptbe.auth.application.dto.PurposeResponse;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.purpose.domain.Purpose;
import kr.co.conceptbe.region.domain.Region;
import kr.co.conceptbe.skill.domain.SkillCategory;

public record FindIdeaWriteResponse(
        List<RegionResponse> regions,
        List<BranchResponse> branches,
        List<PurposeResponse> purposes,
        List<SkillCategoryResponse> skillCategoryResponses
) {

    public static FindIdeaWriteResponse of(
            List<Region> regions,
            List<Branch> branches,
            List<Purpose> purposes,
            List<SkillCategory> skillCategories
    ) {
        return new FindIdeaWriteResponse(
                regions.stream().map(RegionResponse::from).toList(),
                branches.stream().map(BranchResponse::from).toList(),
                purposes.stream().map(PurposeResponse::from).toList(),
                skillCategories.stream()
                        .filter(SkillCategory::isParentSkill)
                        .map(skillCategory -> SkillCategoryResponse.of(skillCategory, skillCategories))
                        .toList()
        );
    }

}
