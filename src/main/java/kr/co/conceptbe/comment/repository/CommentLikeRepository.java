package kr.co.conceptbe.comment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.comment.CommentLike;
import kr.co.conceptbe.member.domain.Member;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
	Optional<CommentLike> findByMemberAndComment(Member member, Comment comment);
}
