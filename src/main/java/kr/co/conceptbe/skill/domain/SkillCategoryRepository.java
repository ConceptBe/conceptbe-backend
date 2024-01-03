package kr.co.conceptbe.skill.domain;

import kr.co.conceptbe.skill.exception.NotFoundSkillCategoryException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {

    default SkillCategory getById(Long id) {
        return findById(id).orElseThrow(
            () -> new NotFoundSkillCategoryException(id)
        );
    }
}
