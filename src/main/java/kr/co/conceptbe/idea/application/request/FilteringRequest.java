package kr.co.conceptbe.idea.application.request;

import java.util.List;

public record FilteringRequest(
        List<Long> branchIds,
        List<Long> purposeIds,
        String cooperationWay,
        Long recruitmentPlace,
        List<Long> skillCategoryIds
) {
}
