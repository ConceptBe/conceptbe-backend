package kr.co.conceptbe.common.entity;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Purpose {

    private static final int PURPOSE_LENGTH_LOWER_BOUND_INCLUSIVE = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Purpose(String name) {
        this.name = name;
    }

    public static Purpose from(String name) {
        validateNull(name);
        validateLength(name);

        return new Purpose(name);
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
