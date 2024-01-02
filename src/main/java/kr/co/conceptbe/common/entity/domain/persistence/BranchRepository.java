package kr.co.conceptbe.common.entity.domain.persistence;

import java.util.List;
import kr.co.conceptbe.common.entity.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    List<Branch> findByIdIn(List<Long> ids);

}
