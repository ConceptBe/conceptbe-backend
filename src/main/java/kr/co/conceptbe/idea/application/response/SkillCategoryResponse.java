package kr.co.conceptbe.idea.application.response;

import java.util.List;
import kr.co.conceptbe.skill.domain.SkillCategory;

public record SkillCategoryResponse(
        Long id,
        String name,
        List<SkillResponse> skillResponses
) {
    public static SkillCategoryResponse of(SkillCategory skillCategory, List<SkillCategory> skillCategories) {
        return new SkillCategoryResponse(
                skillCategory.getId(),
                skillCategory.getName(),
                skillCategories.stream()
                        .filter(childSkill -> isChildSkill(skillCategory, childSkill))
                        .map(SkillResponse::from)
                        .toList()
        );
    }

    private static boolean isChildSkill(SkillCategory parentSkill, SkillCategory childSkill) {
        return parentSkill.getId().equals(childSkill.getId());
    }
}
