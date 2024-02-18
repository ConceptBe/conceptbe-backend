package kr.co.conceptbe.idea.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.domain.MemberSkillCategory;

public record MemberResponse(
    @Schema(description = "프로필 이미지 URL", example = "https://conceptbe.png")
    String profileImageUrl,
    @Schema(description = "닉네임", example = "conceptbe")
    String nickname,
    @Schema(description = "세부스킬들", example = "BE, 서버개발")
    List<String> skills
) {

    public static MemberResponse from(Member member) {
        List<String> skills = member.getSkills()
            .stream()
            .map(MemberSkillCategory::getSkillCategory)
            .map(SkillCategory::getName)
            .toList();

        return new MemberResponse(
            member.getProfileImageUrl(),
            member.getNickname(),
            skills
        );
    }

}
