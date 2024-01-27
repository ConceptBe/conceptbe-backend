package kr.co.conceptbe.idea.presentation.dto.response;

import java.util.List;
import kr.co.conceptbe.teamrecruitment.domain.TeamRecruitmentCategory;

public record TeamRecruitmentCategoryResponse(
        Long id,
        String name,
        List<TeamRecruitmentResponse> teamRecruitmentResponses
) {

    public static TeamRecruitmentCategoryResponse from(TeamRecruitmentCategory teamRecruitmentCategory) {
        return new TeamRecruitmentCategoryResponse(
                teamRecruitmentCategory.getId(),
                teamRecruitmentCategory.getName(),
                teamRecruitmentCategory.getTeamRecruitments()
                        .stream()
                        .map(TeamRecruitmentResponse::from)
                        .toList()
        );
    }

}
