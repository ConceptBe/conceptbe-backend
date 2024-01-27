package kr.co.conceptbe.region.domain.presentation;

import kr.co.conceptbe.region.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
