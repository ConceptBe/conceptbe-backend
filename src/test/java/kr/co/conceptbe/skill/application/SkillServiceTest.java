package kr.co.conceptbe.skill.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import kr.co.conceptbe.skill.application.dto.FindDetailSkillResponse;
import kr.co.conceptbe.skill.application.dto.FindMainSkillResponse;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.skill.domain.SkillCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class SkillServiceTest {

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillCategoryRepository skillCategoryRepository;

    @Test
    void 대분류_스킬을_조회할_수_있다() {
        //given
        SkillCategory mainSkill = skillCategoryRepository.save(new SkillCategory("개발"));
        mainSkill.updateParentSkill(mainSkill);
        skillCategoryRepository.save(new SkillCategory(mainSkill, "BE"));

        //when
        List<FindMainSkillResponse> mainSkills = skillService.getMainSkills();

        //then
        assertAll(
            () -> assertThat(mainSkills.size()).isEqualTo(1),
            () -> assertThat(mainSkills.get(0).name()).isEqualTo("개발")
        );
    }

    @Test
    void 상세분류_스킬을_조회할_수_있다() {
        //given
        SkillCategory mainSkill = skillCategoryRepository.save(new SkillCategory("개발"));
        mainSkill.updateParentSkill(mainSkill);
        skillCategoryRepository.save(new SkillCategory(mainSkill, "BE"));

        //when
        List<FindDetailSkillResponse> detailSkills = skillService.getDetailSkills(mainSkill.getId());

        //then
        assertAll(
            () -> assertThat(detailSkills.size()).isEqualTo(1),
            () -> assertThat(detailSkills.get(0).name()).isEqualTo("BE")
        );
    }
}
