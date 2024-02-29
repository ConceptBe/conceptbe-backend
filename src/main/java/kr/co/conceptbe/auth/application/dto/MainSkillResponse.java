package kr.co.conceptbe.auth.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record MainSkillResponse(
    @Schema(description = "메인 스킬 ID", example = "1")
    Long id,
    @Schema(description = "메인 스킬 이름", example = "개발")
    String name,
    @Schema(description = "세부 스킬 리스트")
    List<DetailSkillResponse> detailSkillResponses
) {
}
