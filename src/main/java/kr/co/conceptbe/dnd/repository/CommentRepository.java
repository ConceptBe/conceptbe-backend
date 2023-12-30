package kr.co.conceptbe.dnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.conceptbe.comment.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
