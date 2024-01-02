package kr.co.conceptbe.comment.dto;

public record CommentCreateRequest (
	Long userId,	// 수정 예정
	Long ideaId,
	Long parentId,
	String content
) {
}
