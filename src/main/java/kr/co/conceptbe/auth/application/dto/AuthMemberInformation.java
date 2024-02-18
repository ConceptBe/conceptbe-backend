package kr.co.conceptbe.auth.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthMemberInformation(
    @Schema(description = "회원 ID", example = "1")
    Long id,
    @Schema(description = "닉네임", example = "conceptbe")
    String nickname,
    @Schema(description = "프로필 이미지 URL", example = "https://conceptbe.png")
    String profileImageUrl
) {
}
