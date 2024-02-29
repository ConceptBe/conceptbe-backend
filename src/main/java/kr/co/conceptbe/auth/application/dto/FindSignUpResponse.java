package kr.co.conceptbe.auth.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record FindSignUpResponse(
    @Schema(description = "메인 스킬 리스트")
    List<MainSkillResponse> mainSkillResponses,
    @Schema(description = "가입 목적 리스트")
    List<PurposeResponse> purposeResponses
) {
}
