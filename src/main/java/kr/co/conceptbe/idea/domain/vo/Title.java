package kr.co.conceptbe.idea.domain.vo;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Title {

    private static final int TITLE_LENGTH_LOWER_BOUND_INCLUSIVE = 1;
    private static final int TITLE_LENGTH_UPPER_BOUND_INCLUSIVE = 20;

    @Column(nullable = true, length = 20)
    private String title;

    private Title(String title) {
        this.title = title;
    }

    public static Title from(String title) {
        validateNull(title);
        title = title.trim();
        validateLength(title);
        return new Title(title);
    }

    private static void validateNull(String title) {
        if (Objects.nonNull(title)) {
            return;
        }

        throw new IllegalArgumentException("제목은 필수로 입력하셔야 합니다.");
    }

    private static void validateLength(String title) {
        if (TITLE_LENGTH_LOWER_BOUND_INCLUSIVE <= title.length()
            && title.length() <= TITLE_LENGTH_UPPER_BOUND_INCLUSIVE) {
            return;
        }

        throw new IllegalArgumentException("제목은 최소 1자에서 최대 20자로 입력하셔야 합니다.");
    }

}
