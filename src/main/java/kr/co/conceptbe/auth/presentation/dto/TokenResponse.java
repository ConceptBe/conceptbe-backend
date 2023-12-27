package kr.co.conceptbe.auth.presentation.dto;

public record TokenResponse(
        String accessToken
) {

    public static TokenResponse from(String accessToken) {
        return new TokenResponse(accessToken);
    }
}
