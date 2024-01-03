package kr.co.conceptbe.skill.domain;

import java.util.Arrays;
import kr.co.conceptbe.skill.exception.NotFoundSkillLevelException;
import lombok.Getter;

@Getter
public enum SkillLevel {

    HIGH("상"),
    MIDDLE("중"),
    LOW("하"),
    ;

    private String name;

    SkillLevel(String name) {
        this.name = name;
    }

    public static SkillLevel from(String inputLevel) {
        return Arrays.stream(values())
            .filter(level -> level.getName().equals(inputLevel))
            .findFirst()
            .orElseThrow(NotFoundSkillLevelException::new);
    }
}
