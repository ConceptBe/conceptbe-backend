package kr.co.conceptbe.auth.application.dto;

public record AuthResponse(
    String accessToken,
    AuthMemberInformation authMemberInformation
) {
}
