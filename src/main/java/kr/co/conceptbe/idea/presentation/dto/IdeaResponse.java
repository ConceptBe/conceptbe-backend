package kr.co.conceptbe.idea.presentation.dto;

import java.util.List;
import kr.co.conceptbe.common.entity.domain.Branch;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaBranch;

public record IdeaResponse(
        Long id,
        List<String> branches,
        String title
) {

    public static IdeaResponse from(Idea idea) {
        List<String> branches = idea.getBranches()
                .stream()
                .map(IdeaBranch::getBranch)
                .map(Branch::getName)
                .toList();

        return new IdeaResponse(
                idea.getId(),
                branches,
                idea.getTitle()
        );
    }

}
