package kr.co.conceptbe.comment.dto;

import java.util.List;
import java.util.stream.Collectors;

import kr.co.conceptbe.comment.Comment;

public record CommentParentResponse (
	String nickname,
	List<String> memberSkills,
	String content,
	Integer likesCount,
	Integer commentCount,
	List<CommentChildResponse> commentChildResponses
) {
	public static CommentParentResponse from(Comment comment) {
		return new CommentParentResponse(
			comment.getCreator().getNickname(),
			comment.getCreator().getSkills().stream().map(e -> e.getSkillCategory().getName()).toList(),
			comment.getContent(),
			comment.getLikesCount(),
			comment.getCommentsCount(),
			comment.getComments()
				.stream()
				.map(CommentChildResponse::from)
				.collect(Collectors.toList())
		);
	}
}
