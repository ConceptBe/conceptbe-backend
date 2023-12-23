package kr.co.conceptbe.common.entity;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class SkillCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_skill_category")
    private SkillCategory parentSkillCategory;

    @Column(nullable = false)
    private String name;

    public SkillCategory(Long id, SkillCategory parentSkillCategory, String name) {
        this.id = id;
        this.parentSkillCategory = parentSkillCategory;
        this.name = name;
    }

}
