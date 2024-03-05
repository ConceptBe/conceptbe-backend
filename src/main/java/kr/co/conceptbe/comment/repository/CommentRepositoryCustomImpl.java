package kr.co.conceptbe.comment.repository;

import static kr.co.conceptbe.comment.QComment.*;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.co.conceptbe.comment.Comment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Comment> getByIdeaId(Long ideaId, Pageable pageable) {
		return jpaQueryFactory.selectFrom(comment)
			.where(comment.idea.id.eq(ideaId))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();
	}
}
