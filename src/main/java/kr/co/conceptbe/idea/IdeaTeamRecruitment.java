package kr.co.conceptbe.idea;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.co.conceptbe.common.entity.TeamRecruitment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class IdeaTeamRecruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    @ManyToOne
    @JoinColumn(name = "team_recruitment")
    private TeamRecruitment teamRecruitment;

    private IdeaTeamRecruitment(Idea idea, TeamRecruitment teamRecruitment) {
        this.idea = idea;
        this.teamRecruitment = teamRecruitment;
    }

    public static IdeaTeamRecruitment of(Idea idea, TeamRecruitment teamRecruitment) {
        return new IdeaTeamRecruitment(idea, teamRecruitment);
    }

}
