package kr.co.conceptbe.member;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.skill.domain.SkillLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class MemberSkillCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(nullable = false, name = "skill_category")
    private SkillCategory skillCategory;

    @Enumerated(STRING)
    @Column(nullable = false)
    private SkillLevel skillLevel;

    public MemberSkillCategory(Member member, SkillCategory skillCategory, SkillLevel skillLevel) {
        this.member = member;
        this.skillCategory = skillCategory;
        this.skillLevel = skillLevel;
    }
}
