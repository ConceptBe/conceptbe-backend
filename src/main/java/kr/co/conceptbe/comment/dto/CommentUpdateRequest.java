package kr.co.conceptbe.comment.dto;

public record CommentUpdateRequest (
	Long userId,	// 수정 예정
	String content
) {
}