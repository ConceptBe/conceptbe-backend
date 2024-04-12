package kr.co.conceptbe.idea.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.conceptbe.member.domain.Member;

public record MemberResponse(
    @Schema(description = "멤버 ID", example = "1")
    Long id,
    @Schema(description = "프로필 이미지 URL", example = "https://conceptbe.png")
    String profileImageUrl,
    @Schema(description = "닉네임", example = "conceptbe")
    String nickname,
    @Schema(description = "세부스킬", example = "서버개발")
    String mainSkill
) {

    public static MemberResponse from(Member member) {
        return new MemberResponse(
            member.getId(),
            member.getProfileImageUrl(),
            member.getNickname(),
            member.getMainSkill().getName()
        );
    }

}
