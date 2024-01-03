package kr.co.conceptbe.idea.domain.vo;


import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.util.List;
import kr.co.conceptbe.purpose.domain.Purpose;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaPurpose;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class IdeaPurposes {

    private static final int IDEA_PURPOSES_SIZE_LOWER_BOUND_INCLUSIVE = 1;

    @OneToMany(mappedBy = "idea")
    private List<IdeaPurpose> ideaPurposes;

    private IdeaPurposes(List<IdeaPurpose> ideaPurposes) {
        this.ideaPurposes = ideaPurposes;
    }

    public static IdeaPurposes of(Idea idea, List<Purpose> purposes) {
        validateSize(purposes);

        List<IdeaPurpose> ideaPurposes = purposes.stream()
                .map(purpose -> IdeaPurpose.of(idea, purpose))
                .toList();

        return new IdeaPurposes(ideaPurposes);
    }

    private static void validateSize(List<Purpose> purposes) {
        if (IDEA_PURPOSES_SIZE_LOWER_BOUND_INCLUSIVE <= purposes.size()) {
            return;
        }

        throw new IllegalArgumentException("목적은 최소 한 개 이상 고르셔야 합니다.");
    }

}
