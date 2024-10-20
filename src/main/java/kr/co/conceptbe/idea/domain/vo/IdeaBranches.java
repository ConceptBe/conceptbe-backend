package kr.co.conceptbe.idea.domain.vo;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaBranch;
import kr.co.conceptbe.idea.exception.EmptyIdeaBracnhesException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class IdeaBranches {

    private static final int IDEA_BRANCHES_SIZE_LOWER_BOUND_INCLUSIVE = 1;

    @OneToMany(
        mappedBy = "idea",
        orphanRemoval = true,
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<IdeaBranch> ideaBranches;

    private IdeaBranches(List<IdeaBranch> ideaBranches) {
        this.ideaBranches = ideaBranches;
    }

    public static IdeaBranches of(Idea idea, List<Branch> branches) {
        validateSize(branches);

        List<IdeaBranch> ideaBranches = branches.stream()
            .map(branch -> IdeaBranch.of(idea, branch))
            .collect(Collectors.toList());

        return new IdeaBranches(ideaBranches);
    }

    public void update(Idea idea, List<Branch> branches) {
        validateSize(branches);
        ideaBranches.clear();
        ideaBranches.addAll(branches.stream()
            .map(branch -> IdeaBranch.of(idea, branch))
            .collect(Collectors.toList()));
    }

    private static void validateSize(List<Branch> branches) {
        if (!branches.isEmpty()) {
            return;
        }

        throw new EmptyIdeaBracnhesException();
    }

}
