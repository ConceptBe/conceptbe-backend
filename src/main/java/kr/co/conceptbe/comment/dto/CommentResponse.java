package kr.co.conceptbe.comment.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommentResponse {
	private String nickname;
	private List<String> memberSkillList;
	private String content;
	private String likesCount;


	@Getter
	@Setter
	@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class CommentParentResponse extends CommentResponse{
		private String commentCount;

		@Builder
		public CommentParentResponse(String nickname, List<String> memberSkillList, String content, String likesCount,
			String commentCount) {
			super(nickname, memberSkillList, content, likesCount);
			this.commentCount = commentCount;
		}
	}
}
