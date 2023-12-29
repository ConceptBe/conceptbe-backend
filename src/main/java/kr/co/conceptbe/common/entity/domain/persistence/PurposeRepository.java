package kr.co.conceptbe.common.entity.domain.persistence;

import java.util.List;
import kr.co.conceptbe.common.entity.domain.Purpose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurposeRepository extends JpaRepository<Purpose, Long> {

    List<Purpose> findByIdIn(List<Long> ids);

}
