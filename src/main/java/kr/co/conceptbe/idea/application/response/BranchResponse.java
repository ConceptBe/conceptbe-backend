package kr.co.conceptbe.idea.application.response;

import kr.co.conceptbe.branch.domain.Branch;

public record BranchResponse(
        Long id,
        String name
) {

    public static BranchResponse from(Branch branch) {
        return new BranchResponse(branch.getId(), branch.getName());
    }

}
