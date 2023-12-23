package kr.co.conceptbe.common.entity;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.co.conceptbe.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class MemberSkillCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "skill_category_id")
    private SkillCategory skillCategory;

    @Column
    @Enumerated(EnumType.STRING)
    private SkillLevel skillLevel;

    public MemberSkillCategory(
            Long id,
            Member member,
            SkillCategory skillCategory,
            SkillLevel skillLevel
    ) {
        this.id = id;
        this.member = member;
        this.skillCategory = skillCategory;
        this.skillLevel = skillLevel;
    }

}
