package kr.co.conceptbe.idea.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.comment.dto.CommentParentResponse;
import kr.co.conceptbe.idea.domain.Idea;

public record IdeaDetailResponse (
	String imageUrl,
	String nickname,
	List<String> skillList,
	String title,
	LocalDateTime date,
	String introduce,
	List<String> branchList,
	List<String> purposeList,
	String cooperationWay,
	String recruitmentPlace,
	List<String> teamRecruitmentsList,
	Integer likesCount,
	Integer commentsCount,
	Integer bookmarksCount,
	Integer hits,
	List<CommentParentResponse> commentParentResponses
) {
	public static IdeaDetailResponse of(Idea idea) {
		return new IdeaDetailResponse(
			idea.getCreator().getProfileImageUrl(),
			idea.getCreator().getNickname(),
			idea.getCreator().getSkills().stream().map(e -> e.getSkillCategory().getName()).toList(),
			idea.getTitle(),
			idea.getCreatedAt(),
			idea.getIntroduce(),
			idea.getBranches().stream().map(e -> e.getBranch().getName()).toList(),
			idea.getPurposes().stream().map(e -> e.getPurpose().getName()).toList(),
			idea.getCooperationWay(),
			idea.getRecruitmentPlace(),
			idea.getTeamRecruitments().stream().map(e -> e.getTeamRecruitment().getName()).toList(),
			idea.getLikesCount(),
			idea.getCommentsCount(),
			idea.getBookmarksCount(),
			idea.getHitsCount(),
			idea.getComments()
			    .stream()
			    .filter(e->e.getParentComment() == null)
			    .map(CommentParentResponse::from)
			    .collect(Collectors.toList())
		);
	}
}
