package kr.co.conceptbe.comment.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import kr.co.conceptbe.comment.Comment;

public record CommentParentResponse (
	Long parentCommentId,
	String nickname,
	String profileImageUrl,
	List<String> memberSkills,
	LocalDateTime createdAt,
	String content,
	Integer likesCount,
	Integer commentCount,
	List<CommentChildResponse> commentChildResponses,
	Boolean owner,
	Boolean deleted,
	Boolean likes
) {
	public static CommentParentResponse of(Comment comment, Long tokenMemberId) {
		return new CommentParentResponse(
			comment.getId(),
			comment.getCreator().getNickname(),
			comment.getCreator().getProfileImageUrl(),
			comment.getCreator().getSkills().stream().map(e -> e.getSkillCategory().getName()).toList(),
			comment.getCreatedAt(),
			comment.getContent(),
			comment.getLikesCount(),
			comment.getCommentsCount(),
			comment.getComments()
				.stream()
				.map(commentParent -> CommentChildResponse.of(commentParent, tokenMemberId))
				.toList(),
			comment.isOwner(tokenMemberId),
			comment.getDeleted(),
			comment.isLike(tokenMemberId));
	}
}
