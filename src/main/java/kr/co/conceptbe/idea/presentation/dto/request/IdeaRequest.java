package kr.co.conceptbe.idea.presentation.dto.request;

import java.util.List;

public record IdeaRequest(
        String title,
        String introduce,
        String recruitmentPlace,
        String cooperationWay,
        List<Long> branchIds,
        List<Long> purposeIds,
        List<Long> teamRecruitmentIds
) {
}
