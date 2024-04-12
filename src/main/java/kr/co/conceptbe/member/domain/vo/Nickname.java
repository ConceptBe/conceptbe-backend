package kr.co.conceptbe.member.domain.vo;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Nickname {

    private static final int NICKNAME_LENGTH_LOWER_BOUND_INCLUSIVE = 2;
    private static final int NICKNAME_LENGTH_UPPER_BOUND_INCLUSIVE = 10;
    private static final String NICKNAME_PATTERN = "/^[a-zA-Z0-9ㄱ-힣]+$/g";

    @Column(nullable = false, length = 10)
    private String nickname;

    private Nickname(String nickname) {
        this.nickname = nickname;
    }

    public static Nickname from(String nickname) {
        validateNull(nickname);
        nickname = nickname.trim();
        validateLength(nickname);
        validatePattern(nickname);
        return new Nickname(nickname);
    }

    private static void validateNull(String nickname) {
        if (Objects.nonNull(nickname)) {
            return;
        }
        throw new IllegalArgumentException("닉네임은 필수로 입력하셔야합니다.");
    }

    private static void validateLength(String nickname) {
        if (NICKNAME_LENGTH_LOWER_BOUND_INCLUSIVE <= nickname.length()
            && nickname.length() <= NICKNAME_LENGTH_UPPER_BOUND_INCLUSIVE) {
            return;
        }
        throw new IllegalArgumentException("닉네임은 2글자에서 10글자 사이여야합니다.");
    }

    private static void validatePattern(String nickname) {
        if (nickname.matches(NICKNAME_PATTERN)) {
            return;
        }
        throw new IllegalArgumentException("닉네임은 한글/영어/숫자 중 구성되어야 합니다.");
    }
}
