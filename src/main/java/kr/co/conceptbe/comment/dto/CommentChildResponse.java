package kr.co.conceptbe.comment.dto;

import java.time.LocalDateTime;
import java.util.List;

import kr.co.conceptbe.comment.Comment;

public record CommentChildResponse(
	Long childCommentId,
	String nickname,
	String profileImageUrl,
	List<String> memberSkills,
	LocalDateTime createdAt,
	String content,
	Integer likesCount,
	Boolean owner,
	Boolean deleted,
	Boolean likes
) {
	public static CommentChildResponse of(Comment comment, Long tokenMemberId) {
		return new CommentChildResponse(
			comment.getId(),
			comment.getCreator().getNickname(),
			comment.getCreator().getProfileImageUrl(),
			comment.getCreator().getSkills().getSkills().stream().map(e -> e.getSkillCategory().getName()).toList(),
			comment.getCreatedAt(),
			comment.getContent(),
			comment.getLikesCount(),
			comment.isOwner(tokenMemberId),
			comment.getDeleted(),
			comment.isLike(tokenMemberId)
		);
	}
}
