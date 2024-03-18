package kr.co.conceptbe.idea.domain.vo;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.idea.domain.IdeaSkillCategory;
import kr.co.conceptbe.skill.domain.SkillCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = PROTECTED)
public class IdeaSkillCategories {

    private static final int IDEA_SKILL_CATEGORIES_SIZE_UPPER_BOUND_INCLUSIVE = 10;

    @OneToMany(
        mappedBy = "idea",
        orphanRemoval = true,
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE}
    )
    private List<IdeaSkillCategory> ideaSkillCategories;

    private IdeaSkillCategories(List<IdeaSkillCategory> ideaSkillCategories) {
        this.ideaSkillCategories = ideaSkillCategories;
    }

    public static IdeaSkillCategories of(Idea idea, List<SkillCategory> skillCategories) {
        validateSize(skillCategories);

        List<IdeaSkillCategory> ideaSkillCategories = skillCategories.stream()
            .map(skillCategory -> IdeaSkillCategory.of(idea, skillCategory))
            .collect(Collectors.toList());

        return new IdeaSkillCategories(ideaSkillCategories);
    }

    public void update(Idea idea, List<SkillCategory> skillCategories) {
        validateSize(skillCategories);
        ideaSkillCategories.clear();
        ideaSkillCategories.addAll(skillCategories.stream()
            .map(skillCategory -> IdeaSkillCategory.of(idea, skillCategory))
            .collect(Collectors.toList()));
    }

    private static void validateSize(List<SkillCategory> skillCategories) {
        if (skillCategories.size() <= IDEA_SKILL_CATEGORIES_SIZE_UPPER_BOUND_INCLUSIVE) {
            return;
        }

        throw new IllegalArgumentException("스킬은 최대 10개 고르실 수 있습니다.");
    }

}
