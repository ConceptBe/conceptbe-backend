package kr.co.conceptbe.idea.presentation.dto.response;

import java.util.List;
import kr.co.conceptbe.auth.application.dto.PurposeResponse;

public record FindIdeaWriteResponse(
        List<RegionResponse> addresses,
        List<BranchResponse> branches,
        List<PurposeResponse> purposeResponses,
        List<TeamRecruitmentCategoryResponse> teamRecruitmentCategories
) {
}
