package kr.co.conceptbe.idea.application.response;

import kr.co.conceptbe.skill.domain.SkillCategory;

public record SkillResponse(
        Long id,
        String name
) {
    public static SkillResponse from(SkillCategory skillCategory) {
        return new SkillResponse(
                skillCategory.getId(),
                skillCategory.getName()
        );
    }
}
