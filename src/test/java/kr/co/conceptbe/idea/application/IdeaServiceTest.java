package kr.co.conceptbe.idea.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import kr.co.conceptbe.idea.application.response.FindIdeaWriteResponse;
import kr.co.conceptbe.idea.application.response.SkillCategoryResponse;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.skill.domain.SkillCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class IdeaServiceTest {

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;
    @Autowired
    private IdeaService ideaService;

    @Test
    void Idea_작성_Filtering_에_필요한_Skill_Categories_를_반환한다() {
        // given
        SkillCategory develop = skillCategoryRepository.save(new SkillCategory("개발"));
        SkillCategory backend = skillCategoryRepository.save(new SkillCategory(develop, "BE"));
        SkillCategory frontend = skillCategoryRepository.save(new SkillCategory(develop, "FE"));

        // when
        FindIdeaWriteResponse response = ideaService.getFindIdeaWriteResponse();
        SkillCategoryResponse skillCategoryResponse = response.skillCategoryResponses().get(0);

        // then
        assertAll(
            () -> assertThat(skillCategoryResponse.id()).isEqualTo(develop.getId()),
            () -> assertThat(skillCategoryResponse.name()).isEqualTo(develop.getName()),
            () -> assertThat(skillCategoryResponse.skillResponses()).hasSize(2),
            () -> assertThat(skillCategoryResponse.skillResponses().get(0).id()).isEqualTo(
                backend.getId()),
            () -> assertThat(skillCategoryResponse.skillResponses().get(0).name()).isEqualTo(
                backend.getName()),
            () -> assertThat(skillCategoryResponse.skillResponses().get(1).id()).isEqualTo(
                frontend.getId()),
            () -> assertThat(skillCategoryResponse.skillResponses().get(1).name()).isEqualTo(
                frontend.getName())
        );
    }
}
