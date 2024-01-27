package kr.co.conceptbe.idea.presentation.dto.response;

import java.util.List;

public record TeamRecruitmentCategoryResponse(
        Long id,
        String name,
        List<TeamRecruitmentResponse> teamRecruitmentResponses
) {
}
