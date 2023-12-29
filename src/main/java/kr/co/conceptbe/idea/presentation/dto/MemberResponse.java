package kr.co.conceptbe.idea.presentation.dto;

import java.util.List;
import kr.co.conceptbe.common.entity.domain.SkillCategory;
import kr.co.conceptbe.member.Member;
import kr.co.conceptbe.member.MemberSkillCategory;

public record MemberResponse(
        String imageUrl,
        String nickname,
        List<String> skills // TODO : 정확히 무엇인지 모르겠어요
) { // TODO : 일단 Mock 으로 구현한 것 입니다.

    public static MemberResponse from(Member member) {
        List<String> skills = member.getSkills()
                .stream()
                .map(MemberSkillCategory::getSkillCategory)
                .map(SkillCategory::getName)
                .toList();

        return new MemberResponse(
                member.getImageUrl(),
                member.getIntroduce(), // TODO : 이름이어야 하는데, 이름이 없네 지금은...
                skills
        );
    }

}
