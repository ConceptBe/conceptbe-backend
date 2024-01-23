package kr.co.conceptbe.auth.application.dto;

public record AuthMemberInformation(
    Long id,
    String nickname,
    String profileImageUrl
) {
}
