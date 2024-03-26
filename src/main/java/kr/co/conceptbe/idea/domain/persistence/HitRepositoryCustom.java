package kr.co.conceptbe.idea.domain.persistence;

import java.util.Optional;

import kr.co.conceptbe.idea.domain.Hit;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.member.domain.Member;

public interface HitRepositoryCustom {
	Optional<Hit> findByMemberAndIdeaOrderByCreatedAtDesc(Member member, Idea idea);
}
