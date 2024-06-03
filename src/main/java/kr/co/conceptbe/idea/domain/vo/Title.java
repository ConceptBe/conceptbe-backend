package kr.co.conceptbe.idea.domain.vo;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import kr.co.conceptbe.idea.exception.EmptyTitleException;
import kr.co.conceptbe.idea.exception.InvalidTitleLengthException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Title {

    private static final int TITLE_LENGTH_LOWER_BOUND_INCLUSIVE = 1;
    private static final int TITLE_LENGTH_UPPER_BOUND_INCLUSIVE = 40;

    @Column(nullable = true, length = 40)
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

        throw new EmptyTitleException();
    }

    private static void validateLength(String title) {
        if (!title.isEmpty() && title.length() <= TITLE_LENGTH_UPPER_BOUND_INCLUSIVE) {
            return;
        }

        throw new InvalidTitleLengthException();
    }

}
