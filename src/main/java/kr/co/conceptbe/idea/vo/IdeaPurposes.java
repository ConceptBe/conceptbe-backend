package kr.co.conceptbe.idea.vo;


import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.conceptbe.idea.Idea;
import kr.co.conceptbe.idea.IdeaPurpose;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = PROTECTED)
@Getter
public class IdeaPurposes {

    private static final int PURPOSES_SIZE_LOWER_BOUND_INCLUSIVE = 1;

    @OneToMany(mappedBy = "idea")
    private List<IdeaPurpose> ideaPurposes;

    private IdeaPurposes(List<IdeaPurpose> ideaPurposes) {
        this.ideaPurposes = ideaPurposes;
    }

    public static IdeaPurposes of(List<String> purposes, Idea idea) {
        validateSize(purposes);

        List<IdeaPurpose> ideaPurposes = purposes.stream()
                .map(branch -> IdeaPurpose.of(branch, idea))
                .collect(Collectors.toList());

        return new IdeaPurposes(ideaPurposes);
    }

    private static void validateSize(List<String> purposes) {
        if (PURPOSES_SIZE_LOWER_BOUND_INCLUSIVE <= purposes.size()) {
            return;
        }

        throw new IllegalArgumentException("목적은 최소 한 개 이상 고르셔야 합니다.");
    }

}
