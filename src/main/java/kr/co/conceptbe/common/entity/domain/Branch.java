package kr.co.conceptbe.common.entity.domain;

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
public class Branch {

    private static final int BRANCH_LENGTH_LOWER_BOUND_INCLUSIVE = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Branch(String name) {
        this.name = name;
    }

    public static Branch from(String name) {
        validateNull(name);
        validateLength(name);

        return new Branch(name);
    }

    private static void validateNull(String name) {
        if (Objects.nonNull(name)) {
            return;
        }

        throw new IllegalArgumentException("분야는 필수로 입력되어야 합니다.");
    }

    private static void validateLength(String name) {
        if (BRANCH_LENGTH_LOWER_BOUND_INCLUSIVE <= name.length()) {
            return;
        }

        throw new IllegalArgumentException("분야의 이름은 최소 1자 이상이어야 합니다.");
    }

}
