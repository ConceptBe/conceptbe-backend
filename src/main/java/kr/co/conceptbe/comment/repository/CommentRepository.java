package kr.co.conceptbe.comment.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.idea.domain.Idea;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	Page<Comment> findByIdea(Idea idea);

	default Comment getById(Long commentId) {
		return findById(commentId).orElseThrow(
			() -> new IllegalArgumentException("Not Found ID : " + commentId));
	}
}
