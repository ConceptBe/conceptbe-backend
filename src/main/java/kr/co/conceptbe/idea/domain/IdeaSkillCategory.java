package kr.co.conceptbe.idea.domain;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.co.conceptbe.skill.domain.SkillCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class IdeaSkillCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    @ManyToOne
    @JoinColumn(name = "skill_category")
    private SkillCategory skillCategory;

    private IdeaSkillCategory(Idea idea, SkillCategory skillCategory) {
        this.idea = idea;
        this.skillCategory = skillCategory;
    }

    public static IdeaSkillCategory of(Idea idea, SkillCategory skillCategory) {
        return new IdeaSkillCategory(idea, skillCategory);
    }

}
