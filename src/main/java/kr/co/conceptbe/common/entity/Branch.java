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
public class Branch {

    private static final int BRANCH_LENGTH_LOWER_BOUND_INCLUSIVE = 1;

    @Column(nullable = false)
    private String branch;

    private Branch(String branch) {
        this.branch = branch;
    }

    public static Branch from(String branch) {
        validateNull(branch);
        validateLength(branch);

        return new Branch(branch);
    }

    private static void validateNull(String branch) {
        if (Objects.nonNull(branch)) {
            return;
        }

        throw new IllegalArgumentException("분야는 필수로 입력되어야 합니다.");
    }

    private static void validateLength(String branch) {
        if (BRANCH_LENGTH_LOWER_BOUND_INCLUSIVE <= branch.length()) {
            return;
        }

        throw new IllegalArgumentException("분야의 이름은 최소 1자 이상이어야 합니다.");
    }

}
