package kr.co.conceptbe.comment.dto;

import java.util.List;

import kr.co.conceptbe.comment.Comment;

public record CommentChildResponse(
	String nickname,
	List<String> memberSkills,
	String content,
	Integer likesCount,
	Boolean owner
) {
	public static CommentChildResponse of(Comment comment, Long tokenMemberId) {
		return new CommentChildResponse(
			comment.getCreator().getNickname(),
			comment.getCreator().getSkills().stream().map(e -> e.getSkillCategory().getName()).toList(),
			comment.getContent(),
			comment.getLikesCount(),
			comment.isOwner(tokenMemberId)
		);
	}
}
