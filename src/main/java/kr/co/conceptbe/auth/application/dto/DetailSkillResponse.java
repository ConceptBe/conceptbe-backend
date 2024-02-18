package kr.co.conceptbe.auth.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record DetailSkillResponse(
    @Schema(description = "세부 스킬 ID", example = "1")
    Long id,
    @Schema(description = "세부 스킬 이름", example = "BE")
    String name
) {
}
