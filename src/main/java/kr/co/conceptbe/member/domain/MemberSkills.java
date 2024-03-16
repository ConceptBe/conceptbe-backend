package kr.co.conceptbe.member.domain;

import static jakarta.persistence.CascadeType.REMOVE;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.skill.domain.SkillLevel;
import kr.co.conceptbe.skill.exception.DuplicatedSkillCategoryException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class MemberSkills {

    @OneToMany(mappedBy = "member",  cascade = {CascadeType.PERSIST, REMOVE}, orphanRemoval = true)
    private List<MemberSkillCategory> skills = new ArrayList<>();

    private MemberSkills(List<MemberSkillCategory> memberSkillCategories) {
        this.skills = memberSkillCategories;
    }

    public static MemberSkills of(Member member, List<SkillCategory> skillCategories, List<SkillLevel> skillLevels) {
        validateDuplication(skillCategories);

        List<MemberSkillCategory> skills = new ArrayList<>();
        for (int index = 0; index < skillCategories.size(); index++) {
            skills.add(new MemberSkillCategory(
                member, skillCategories.get(index), skillLevels.get(index))
            );
        }

        return new MemberSkills(skills);
    }

    private static void validateDuplication(List<SkillCategory> skillCategories) {
        for (int standard = 0; standard < skillCategories.size(); standard++) {
            for (int other = 0; other < skillCategories.size(); other++) {
                if (standard == other) {
                    continue;
                }
                if (Objects.equals(skillCategories.get(standard).getId(), skillCategories.get(other).getId())) {
                    throw new DuplicatedSkillCategoryException();
                }
            }
        }
    }

    public void clear() {
        skills.clear();
    }
}
