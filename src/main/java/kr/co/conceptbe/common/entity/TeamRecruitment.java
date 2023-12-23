package kr.co.conceptbe.common.entity;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TeamRecruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "team_recruitment_category_id")
    private TeamRecruitmentCategory teamRecruitmentCategory;

    public TeamRecruitment(Long id, String name, TeamRecruitmentCategory teamRecruitmentCategory) {
        this.id = id;
        this.name = name;
        this.teamRecruitmentCategory = teamRecruitmentCategory;
    }

}
