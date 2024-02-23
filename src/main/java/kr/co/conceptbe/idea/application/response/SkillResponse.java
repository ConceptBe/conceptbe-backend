package kr.co.conceptbe.idea.application.response;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.conceptbe.skill.domain.SkillCategory;

public record SkillResponse(
        @Schema(description = "팀원 모집 분야 ID(상세 분야)", example = "1")
        Long id,
        @Schema(description = "팀원 모집 분야 이름(상세 분야)", example = "BE")
        String name
) {
    public static SkillResponse from(SkillCategory skillCategory) {
        return new SkillResponse(
                skillCategory.getId(),
                skillCategory.getName()
        );
    }
}
