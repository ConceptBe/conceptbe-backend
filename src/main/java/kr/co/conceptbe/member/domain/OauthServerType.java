package kr.co.conceptbe.member.domain;

import java.util.Arrays;
import kr.co.conceptbe.member.exception.NotFoundOauthServerTypeException;

public enum OauthServerType {
    KAKAO,
    NAVER,
    ;

    public static OauthServerType from(String type) {
        return Arrays.stream(values())
            .filter(server -> server.name().equals(type.toUpperCase()))
            .findFirst()
            .orElseThrow(NotFoundOauthServerTypeException::new);
    }
}
