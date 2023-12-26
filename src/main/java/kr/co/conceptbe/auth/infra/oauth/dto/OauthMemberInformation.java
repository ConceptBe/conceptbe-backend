package kr.co.conceptbe.auth.infra.oauth.dto;

public record OauthMemberInformation(
    Long oauthId,
    String email,
    String nickname,
    String profileImageUrl
) {
}
