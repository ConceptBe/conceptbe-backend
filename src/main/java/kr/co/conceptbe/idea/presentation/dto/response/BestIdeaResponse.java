package kr.co.conceptbe.idea.presentation.dto.response;

import java.util.List;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaBranch;

public record BestIdeaResponse(
        Long id,
        List<String> branches,
        String title
) {

    public static BestIdeaResponse from(Idea idea) {

        return new BestIdeaResponse(
                idea.getId(),
                getBranches(idea.getBranches()),
                idea.getTitle()
        );
    }

    private static List<String> getBranches(List<IdeaBranch> branches) {
        return branches.stream()
                .map(IdeaBranch::getBranch)
                .map(Branch::getName)
                .toList();
    }

}
