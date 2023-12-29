package kr.co.conceptbe.common.entity;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Purpose {

    private static final int PURPOSE_LENGTH_LOWER_BOUND_INCLUSIVE = 1;

    @Column(nullable = false)
    private String purpose;

    private Purpose(String purpose) {
        this.purpose = purpose;
    }

    public static Purpose from(String purpose) {
        validateNull(purpose);
        validateLength(purpose);

        return new Purpose(purpose);
    }

    private static void validateNull(String purpose) {
        if (Objects.nonNull(purpose)) {
            return;
        }

        throw new IllegalArgumentException("목적은 필수로 입력되어야 합니다.");
    }

    private static void validateLength(String purpose) {
        if (PURPOSE_LENGTH_LOWER_BOUND_INCLUSIVE <= purpose.length()) {
            return;
        }

        throw new IllegalArgumentException("목적의 이름은 최소 1자 이상이어야 합니다.");
    }

}
