package kr.co.conceptbe.idea.application.request;

import java.util.List;

public record IdeaRequest(
        String title,
        String introduce,
        String cooperationWay,
        Long recruitmentPlaceId,
        List<Long> branchIds,
        List<Long> purposeIds,
        List<Long> skillCategoryIds
) {
}
