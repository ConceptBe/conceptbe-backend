package kr.co.conceptbe.teamrecruitment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamRecruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_recruitment_category_id")
    private TeamRecruitmentCategory teamRecruitmentCategory;

    public TeamRecruitment(String name, TeamRecruitmentCategory teamRecruitmentCategory) {
        this.name = name;
        this.teamRecruitmentCategory = teamRecruitmentCategory;
    }

}
