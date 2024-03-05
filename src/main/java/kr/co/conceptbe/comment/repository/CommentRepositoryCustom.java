package kr.co.conceptbe.comment.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import kr.co.conceptbe.comment.Comment;

public interface CommentRepositoryCustom {
	List<Comment> getByIdeaId(Long ideaId, Pageable pageable);
}
