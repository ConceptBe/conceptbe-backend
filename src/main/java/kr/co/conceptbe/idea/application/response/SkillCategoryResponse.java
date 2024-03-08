package kr.co.conceptbe.idea.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import kr.co.conceptbe.skill.domain.SkillCategory;

public record SkillCategoryResponse(
    @Schema(description = "팀원 모집 분야 ID(카테고리)", example = "1")
    Long id,
    @Schema(description = "팀원 모집 분야 이름(카테고리)", example = "개발")
    String name,
    @Schema(description = "팀원 모집 분야 이름(세부분야)")
    List<SkillResponse> skillResponses
) {

    public static SkillCategoryResponse of(
        SkillCategory skillCategory,
        List<SkillCategory> skillCategories
    ) {
        return new SkillCategoryResponse(
            skillCategory.getId(),
            skillCategory.getName(),
            skillCategories.stream()
                .filter(childSkill -> !childSkill.isParentSkill())
                .filter(childSkill -> childSkill.isChildSkill(skillCategory))
                .map(SkillResponse::from)
                .toList()
        );
    }
}
