package kr.co.conceptbe.skill.domain;

import java.util.List;
import kr.co.conceptbe.skill.exception.NotFoundSkillCategoryException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {

    default SkillCategory getById(Long id) {
        return findById(id).orElseThrow(
            () -> new NotFoundSkillCategoryException(id)
        );
    }

    @Query("select s from SkillCategory s where s.id = s.parentSkillCategory.id")
    List<SkillCategory> findMainSkills();

    @Query("select s from SkillCategory s where s.id != :parentSkillId and s.parentSkillCategory.id = :parentSkillId")
    List<SkillCategory> findDetailSkills(Long parentSkillId);
}
