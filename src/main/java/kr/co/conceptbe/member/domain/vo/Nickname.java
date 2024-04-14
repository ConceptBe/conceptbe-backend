package kr.co.conceptbe.member.domain.vo;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;
import kr.co.conceptbe.member.exception.EmptyNicknameException;
import kr.co.conceptbe.member.exception.InvalidNicknameLengthException;
import kr.co.conceptbe.member.exception.InvalidNicknamePatternException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Nickname {

    private static final int NICKNAME_LENGTH_LOWER_BOUND_INCLUSIVE = 2;
    private static final int NICKNAME_LENGTH_UPPER_BOUND_INCLUSIVE = 10;
    private static final Pattern NICKNAME_PATTERN = Pattern.compile("^[가-힣a-zA-Z0-9]*$");

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
        throw new EmptyNicknameException();
    }

    private static void validateLength(String nickname) {
        if (NICKNAME_LENGTH_LOWER_BOUND_INCLUSIVE <= nickname.length()
            && nickname.length() <= NICKNAME_LENGTH_UPPER_BOUND_INCLUSIVE) {
            return;
        }
        throw new InvalidNicknameLengthException();
    }

    private static void validatePattern(String nickname) {
        if (NICKNAME_PATTERN.matcher(nickname).find()) {
            return;
        }
        throw new InvalidNicknamePatternException();
    }
}
