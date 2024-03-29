package kr.co.conceptbe.skill.domain;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class SkillCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_skill_category_id")
    private SkillCategory parentSkillCategory;

    @Column(nullable = false)
    private String name;

    public SkillCategory(SkillCategory parentSkillCategory, String name) {
        this.parentSkillCategory = parentSkillCategory;
        this.name = name;
    }

    public SkillCategory(String name) {
        this.name = name;
    }

    public void updateParentSkill(SkillCategory parentSkill) {
        this.parentSkillCategory = parentSkill;
    }

    public boolean isParentSkill() {
        return Objects.isNull(parentSkillCategory) || id.equals(parentSkillCategory.getId());
    }

    public boolean isChildSkill(SkillCategory skillCategory) {
        return !isParentSkill() && skillCategory.id.equals(parentSkillCategory.id);
    }
}
