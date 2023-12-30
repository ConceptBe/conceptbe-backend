package kr.co.conceptbe.auth.application.dto;

import kr.co.conceptbe.auth.infra.oauth.dto.OauthMemberInformation;

public record OauthMemberResponse(
    boolean isMember,
    OauthMemberInformation oauthMemberInformation
) {
}
