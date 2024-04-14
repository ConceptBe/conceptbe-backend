package kr.co.conceptbe.idea.domain.vo;


import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaPurpose;
import kr.co.conceptbe.idea.exception.EmptyIdeaPurposesException;
import kr.co.conceptbe.purpose.domain.Purpose;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class IdeaPurposes {

    private static final int IDEA_PURPOSES_SIZE_LOWER_BOUND_INCLUSIVE = 1;

    @OneToMany(
        mappedBy = "idea",
        orphanRemoval = true,
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<IdeaPurpose> ideaPurposes;

    private IdeaPurposes(List<IdeaPurpose> ideaPurposes) {
        this.ideaPurposes = ideaPurposes;
    }

    public static IdeaPurposes of(Idea idea, List<Purpose> purposes) {
        validateSize(purposes);

        List<IdeaPurpose> ideaPurposes = purposes.stream()
            .map(purpose -> IdeaPurpose.of(idea, purpose))
            .collect(Collectors.toList());

        return new IdeaPurposes(ideaPurposes);
    }

    public void update(Idea idea, List<Purpose> purposes) {
        validateSize(purposes);
        ideaPurposes.clear();
        ideaPurposes.addAll(purposes.stream()
            .map(purpose -> IdeaPurpose.of(idea, purpose))
            .collect(Collectors.toList()));
    }

    private static void validateSize(List<Purpose> purposes) {
        if (!purposes.isEmpty()) {
            return;
        }

        throw new EmptyIdeaPurposesException();
    }

}
