package kr.co.conceptbe.idea.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.conceptbe.idea.domain.Hit;

@Repository
public interface HitRepository extends JpaRepository<Hit, Long> {
}
