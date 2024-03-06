package kr.co.conceptbe.idea.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaBranch;

public record BestIdeaResponse(
        @Schema(description = "인기 게시글 ID", example = "1")
        Long id,
        @Schema(description = "인기 게시글 분야 목록", example = "IT, 유튜브 컨텐츠")
        List<String> branches,
        @Schema(description = "게시글 제목", example = "저랑 같이 플젝할 사람")
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
