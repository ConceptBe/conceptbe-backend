package kr.co.conceptbe.purpose.domain.persistence;

import java.util.List;
import kr.co.conceptbe.purpose.domain.Purpose;
import kr.co.conceptbe.skill.exception.NotFoundSkillCategoryException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurposeRepository extends JpaRepository<Purpose, Long> {

    List<Purpose> findByIdIn(List<Long> ids);

    default Purpose getById(Long id) {
        return findById(id).orElseThrow(
            () -> new NotFoundSkillCategoryException(id)
        );
    }
}
