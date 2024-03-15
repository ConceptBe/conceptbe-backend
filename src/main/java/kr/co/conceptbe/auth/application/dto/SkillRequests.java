package kr.co.conceptbe.auth.application.dto;

import java.util.List;
import java.util.Objects;
import kr.co.conceptbe.skill.exception.DuplicatedSkillCategoryException;

public record SkillRequests(
    List<SkillRequest> skills
) {

    public SkillRequests(List<SkillRequest> skills) {
        checkDuplicatedSkillCategory(skills);
        this.skills = skills;
    }

    private void checkDuplicatedSkillCategory(List<SkillRequest> skills) {
        for (int standard = 0; standard < skills.size(); standard++) {
            for (int other = 0; other < skills.size(); other++) {
                if (standard == other) {
                    continue;
                }
                if (Objects.equals(skills.get(standard).skillId(), skills.get(other).skillId())) {
                    throw new DuplicatedSkillCategoryException();
                }
            }
        }
    }
}
