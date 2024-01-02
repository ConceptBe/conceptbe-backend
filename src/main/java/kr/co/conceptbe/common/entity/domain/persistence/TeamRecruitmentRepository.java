package kr.co.conceptbe.common.entity.domain.persistence;

import java.util.List;
import kr.co.conceptbe.common.entity.domain.TeamRecruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRecruitmentRepository extends JpaRepository<TeamRecruitment, Long> {

    List<TeamRecruitment> findByIdIn(List<Long> ids);

}
