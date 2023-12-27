package kr.co.conceptbe.auth.infra.oauth.dto;

public record OauthMemberInformation(
    String oauthId,
    String email,
    String nickname,
    String profileImageUrl
) {
}
