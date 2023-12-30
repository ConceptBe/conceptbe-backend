package kr.co.conceptbe.idea.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import kr.co.conceptbe.comment.dto.CommentResponse;
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
public class IdeaDetailResponse {
	private String imageUrl;
	private String nickname;
	private List<String> skillList;
	private String title;
	private String date;
	private String introduce;
	private List<String> branchList;
	private List<String> purposeList;
	private String cooperationWay;
	private String recruitmentPlace;
	private List<String> teamRecruitmentsList;
	private Integer likesCount;
	private Integer commentsCount;
	private Integer bookmarksCount;
	private Integer hits;
	private List<CommentResponse.CommentParentResponse> commentParentResponseList;
}
