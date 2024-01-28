package kr.co.conceptbe.idea.application.request;

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
