package kr.co.conceptbe.comment.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateRequest (
	@NotBlank(message = "댓글이 입력되지 않았습니다.")
	String content
) {
}