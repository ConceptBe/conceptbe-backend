package kr.co.conceptbe.branch.domain;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;
import kr.co.conceptbe.idea.exception.EmptyBranchNameException;
import kr.co.conceptbe.idea.exception.InvalidBranchLengthException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
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

        throw new EmptyBranchNameException();
    }

    private static void validateLength(String name) {
        if (BRANCH_LENGTH_LOWER_BOUND_INCLUSIVE <= name.length()) {
            return;
        }

        throw new InvalidBranchLengthException();
    }

}
