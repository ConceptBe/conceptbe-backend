package kr.co.conceptbe.member.persistence;

import kr.co.conceptbe.member.domain.MemberSkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSkillRepository extends JpaRepository<MemberSkillCategory, Long> {
}
