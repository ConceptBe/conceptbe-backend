package kr.co.conceptbe.auth.infra.oauth.dto;

import kr.co.conceptbe.member.OauthServerType;

public record OauthMemberInformation(
    String oauthId,
    OauthServerType oauthServerType,
    String email,
    String nickname,
    String profileImageUrl
) {
}
