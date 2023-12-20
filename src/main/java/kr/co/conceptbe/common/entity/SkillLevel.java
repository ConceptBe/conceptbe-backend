package kr.co.conceptbe.common.entity;

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

}
