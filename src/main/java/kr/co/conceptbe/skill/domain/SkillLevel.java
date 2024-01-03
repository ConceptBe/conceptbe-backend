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

    public static SkillLevel from(String type) {
        return Arrays.stream(values())
            .filter(server -> server.name().equals(type))
            .findFirst()
            .orElseThrow(NotFoundSkillLevelException::new);
    }
}
