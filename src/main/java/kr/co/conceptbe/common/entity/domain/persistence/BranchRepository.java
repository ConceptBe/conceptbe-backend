package kr.co.conceptbe.common.entity.domain.persistence;

import kr.co.conceptbe.common.entity.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
