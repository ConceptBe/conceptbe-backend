package kr.co.conceptbe.comment.dto;

import java.util.List;

import kr.co.conceptbe.comment.Comment;

public record CommentChildResponse(
	String nickname,
	List<String> memberSkills,
	String content,
	Integer likesCount
) {
	public static CommentChildResponse from(Comment comment) {
		return new CommentChildResponse(
			comment.getCreator().getNickname(),
			comment.getCreator().getSkills().stream().map(e -> e.getSkillCategory().getName()).toList(),
			comment.getContent(),
			comment.getLikesCount()
		);
	}
}
