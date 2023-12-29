package kr.co.conceptbe.idea.vo;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.conceptbe.idea.Idea;
import kr.co.conceptbe.idea.IdeaBranch;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
@Getter
public class IdeaBranches {

    private static final int BRANCHES_SIZE_LOWER_BOUND_INCLUSIVE = 1;

    @OneToMany(mappedBy = "idea")
    private List<IdeaBranch> branches;

    private IdeaBranches(List<IdeaBranch> branches) {
        this.branches = branches;
    }

    public static IdeaBranches of(List<String> branches, Idea idea) {
        validateSize(branches);

        List<IdeaBranch> ideaBranches = branches.stream()
                .map(branch -> IdeaBranch.of(branch, idea))
                .collect(Collectors.toList());

        return new IdeaBranches(ideaBranches);
    }

    private static void validateSize(List<String> branches) {
        if (BRANCHES_SIZE_LOWER_BOUND_INCLUSIVE <= branches.size()) {
            return;
        }

        throw new IllegalArgumentException("분야는 최소 한 개 이상 고르셔야 합니다.");
    }

}
