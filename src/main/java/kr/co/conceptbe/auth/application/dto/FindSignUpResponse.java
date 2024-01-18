package kr.co.conceptbe.auth.application.dto;

import java.util.List;

public record FindSignUpResponse(
    List<MainSkillResponse> mainSkillResponses,
    List<PurposeResponse> purposeResponses
) {
}
