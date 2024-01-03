package kr.co.conceptbe.comment.dto;

public record CommentCreateRequest (
	Long ideaId,
	Long parentId,
	String content
) {
}
