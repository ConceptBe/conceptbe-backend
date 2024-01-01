package kr.co.conceptbe.idea.domain.vo;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Introduce {

    private static final int INTRODUCE_LENGTH_LOWER_BOUND_INCLUSIVE = 10;
    private static final int INTRODUCE_LENGTH_UPPER_BOUND_INCLUSIVE = 2000;

    @Lob
    @Column(nullable = false, length = 2000)
    private String introduce;

    private Introduce(String introduce) {
        this.introduce = introduce;
    }

    public static Introduce from(String introduce) {
        validateNull(introduce);
        validateLength(introduce);

        return new Introduce(introduce);
    }

    private static void validateNull(String introduce) {
        if (Objects.nonNull(introduce)) {
            return;
        }

        throw new IllegalArgumentException("본문은 필수로 입력하셔야 합니다.");
    }

    private static void validateLength(String introduce) {
        if (INTRODUCE_LENGTH_LOWER_BOUND_INCLUSIVE <= introduce.length()
                && introduce.length() <= INTRODUCE_LENGTH_UPPER_BOUND_INCLUSIVE) {
            return;
        }

        throw new IllegalArgumentException("본문은 최소 10자에서 최대 2000자로 입력하셔야 합니다.");
    }

}
