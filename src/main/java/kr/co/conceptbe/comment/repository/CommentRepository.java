package kr.co.conceptbe.comment.repository;

import java.util.List;
import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	Page<Comment> findByIdea(Idea idea, Pageable pageable);

	default Comment getById(Long commentId) {
		return findById(commentId).orElseThrow(
			() -> new IllegalArgumentException("Not Found ID : " + commentId));
	}

	List<Comment> findByCreator(Member member);
}
