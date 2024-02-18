package kr.co.conceptbe.auth.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.conceptbe.purpose.domain.Purpose;

public record PurposeResponse(
    @Schema(description = "목적 ID", example = "1")
    Long id,
    @Schema(description = "목적 이름", example = "창업")
    String name
) {

    public static PurposeResponse from(Purpose purpose) {
        return new PurposeResponse(purpose.getId(), purpose.getName());
    }

}
