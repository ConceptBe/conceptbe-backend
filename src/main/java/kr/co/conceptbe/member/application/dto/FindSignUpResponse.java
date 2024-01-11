package kr.co.conceptbe.member.application.dto;

import java.util.List;

public record FindSignUpResponse(
    List<MainSkillResponse> mainSkillResponses,
    List<PurposeResponse> purposeResponses
) {
}
