package kr.co.conceptbe.idea.application.response;

import kr.co.conceptbe.teamrecruitment.domain.TeamRecruitment;

public record TeamRecruitmentResponse(
        Long id,
        String name
) {

    public static TeamRecruitmentResponse from(TeamRecruitment teamRecruitment) {
        return new TeamRecruitmentResponse(
                teamRecruitment.getId(),
                teamRecruitment.getName()
        );
    }

}