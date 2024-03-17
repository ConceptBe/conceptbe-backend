package kr.co.conceptbe.idea.application.request;

import java.util.List;
import kr.co.conceptbe.idea.domain.vo.CooperationWay;

public record FilteringRequest(
        List<Long> branchIds,
        List<Long> purposeIds,
        CooperationWay cooperationWay,
        Long recruitmentPlace,
        List<Long> skillCategoryIds
) {
}
