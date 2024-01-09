package kr.co.conceptbe.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record CommentCreateRequest (
	@Positive(message = "게시글 ID는 양수 입니다.")
	Long ideaId,
	@PositiveOrZero(message = "게시글 ID는 양수 입니다.")
	Long parentId,
	@NotBlank(message = "댓글이 입력되지 않았습니다.")
	String content
) {
}
