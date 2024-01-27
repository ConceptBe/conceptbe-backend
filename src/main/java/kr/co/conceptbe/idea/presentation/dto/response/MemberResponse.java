package kr.co.conceptbe.idea.presentation.dto.response;

import java.util.List;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.member.domain.MemberSkillCategory;

public record MemberResponse(
        String profileImageUrl,
        String nickname,
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
