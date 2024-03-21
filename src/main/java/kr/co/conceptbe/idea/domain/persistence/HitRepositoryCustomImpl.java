package kr.co.conceptbe.idea.domain.persistence;

import static kr.co.conceptbe.idea.domain.QHit.*;

import java.util.Optional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.conceptbe.idea.domain.Hit;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.member.domain.Member;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HitRepositoryCustomImpl implements HitRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<Hit> findByMemberAndIdeaOrderByCreatedAtDesc(Member member, Idea idea) {
		return Optional.ofNullable(
			jpaQueryFactory.selectFrom(hit)
			.where(
				hit.member.eq(member),
				hit.idea.eq(idea)
			)
			.orderBy(hit.createdAt.desc())
			.limit(1)
			.fetchOne());
	}
}
