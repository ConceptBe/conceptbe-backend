package kr.co.conceptbe.auth.infra.oauth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.conceptbe.member.domain.OauthServerType;

public record OauthMemberInformation(
    @Schema(description = "Oauth ID", example = "1234567890")
    String oauthId,
    @Schema(description = "Oauth 서버 타입", example = "KAKAO")
    OauthServerType oauthServerType,
    @Schema(description = "이메일", example = "conceptbe@gmail.com")
    String email,
    @Schema(description = "닉네임", example = "conceptbe")
    String nickname,
    @Schema(description = "프로필 이미지 URL", example = "https://conceptbe.png")
    String profileImageUrl
) {
}
