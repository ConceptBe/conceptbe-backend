package kr.co.conceptbe.member.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record MemberProfileSkillResponse(
    @Schema(description = "스킬id", example = "1")
    Long skillId,
    @Schema(description = "스킬명", example = "BE")
    String skillName,
    @Schema(description = "스킬레벨", example = "상")
    String level
) {
}
