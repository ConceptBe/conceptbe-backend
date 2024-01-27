package kr.co.conceptbe.auth.application.dto;

import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.idea.presentation.dto.response.BranchResponse;
import kr.co.conceptbe.purpose.domain.Purpose;

public record PurposeResponse(
    Long id,
    String name
) {

    public static PurposeResponse from(Purpose purpose) {
        return new PurposeResponse(purpose.getId(), purpose.getName());
    }

}
