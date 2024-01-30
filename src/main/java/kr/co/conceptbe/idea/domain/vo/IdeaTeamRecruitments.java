package kr.co.conceptbe.idea.domain.vo;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.util.List;
import kr.co.conceptbe.teamrecruitment.domain.TeamRecruitment;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaTeamRecruitment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class IdeaTeamRecruitments {

    private static final int IDEA_TEAM_RECRUITMENTS_SIZE_UPPER_BOUND_INCLUSIVE = 10;

    @OneToMany(mappedBy = "idea")
    private List<IdeaTeamRecruitment> ideaTeamRecruitments;

    private IdeaTeamRecruitments(List<IdeaTeamRecruitment> ideaTeamRecruitments) {
        this.ideaTeamRecruitments = ideaTeamRecruitments;
    }

    public static IdeaTeamRecruitments of(Idea idea, List<TeamRecruitment> teamRecruitments) {
        validateSize(teamRecruitments);

        List<IdeaTeamRecruitment> ideaTeamRecruitments = teamRecruitments.stream()
                .map(teamRecruitment -> IdeaTeamRecruitment.of(idea, teamRecruitment))
                .toList();

        return new IdeaTeamRecruitments(ideaTeamRecruitments);
    }

    private static void validateSize(List<TeamRecruitment> teamRecruitments) {
        if (teamRecruitments.size() <= IDEA_TEAM_RECRUITMENTS_SIZE_UPPER_BOUND_INCLUSIVE) {
            return;
        }

        throw new IllegalArgumentException("팀원은 최대 10명 고르실 수 있습니다.");
    }

}
