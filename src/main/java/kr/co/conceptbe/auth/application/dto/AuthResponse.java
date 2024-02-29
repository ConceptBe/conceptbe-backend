package kr.co.conceptbe.auth.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthResponse(
    @Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
    String accessToken,
    AuthMemberInformation authMemberInformation
) {
}
