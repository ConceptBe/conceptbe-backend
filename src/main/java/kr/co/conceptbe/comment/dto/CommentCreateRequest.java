package kr.co.conceptbe.comment.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommentCreateRequest {
	private Long userId;	// 수정 예정
	private Long ideaId;
	private Long parentId;
	private String content;
}
