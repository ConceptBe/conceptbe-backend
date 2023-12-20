package kr.co.conceptbe.common.entity;

import lombok.Getter;

@Getter
public enum Level {

    HIGH("상"),
    MIDDLE("중"),
    LOW("하"),
    ;

    private String name;

    Level(String name) {
        this.name = name;
    }

}
