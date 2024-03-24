package kr.co.conceptbe.idea.domain.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.conceptbe.idea.domain.Hit;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.member.domain.Member;

@Repository
public interface HitRepository extends JpaRepository<Hit, Long>, HitRepositoryCustom {
	Optional<Hit> findFirstByMemberAndIdeaOrderByCreatedAtDesc(Member member, Idea idea);
}
