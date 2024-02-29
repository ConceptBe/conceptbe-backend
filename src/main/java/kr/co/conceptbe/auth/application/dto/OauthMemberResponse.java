package kr.co.conceptbe.auth.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.conceptbe.auth.infra.oauth.dto.OauthMemberInformation;

public record OauthMemberResponse(
    @Schema(description = "회원여부", example = "true")
    boolean isMember,
    OauthMemberInformation oauthMemberInformation
) {
}
