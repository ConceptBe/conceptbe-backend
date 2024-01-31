package kr.co.conceptbe.idea.dto;

import java.time.LocalDateTime;
import java.util.List;
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
	List<CommentParentResponse> commentParentResponses,
	Boolean owner
) {
	public static IdeaDetailResponse of(Long tokenMemberId, Idea idea) {
		return new IdeaDetailResponse(
			idea.getCreator().getProfileImageUrl(),
			idea.getCreator().getNickname(),
			idea.getCreator().getSkills().stream().map(e -> e.getSkillCategory().getName()).toList(),
			idea.getTitle(),
			idea.getCreatedAt(),
			idea.getIntroduce(),
			idea.getBranches().stream().map(e -> e.getBranch().getName()).toList(),
			idea.getPurposes().stream().map(e -> e.getPurpose().getName()).toList(),
			idea.getCooperationWay().getValue(),
			idea.getRecruitmentPlace().getName(),
			idea.getTeamRecruitments().stream().map(e -> e.getTeamRecruitment().getName()).toList(),
			idea.getLikesCount(),
			idea.getCommentsCount(),
			idea.getBookmarksCount(),
			idea.getHitsCount(),
			idea.getComments()
					.stream()
					.filter(e->e.getParentComment() == null)
					.map(CommentParentResponse::from)
					.toList(),
			idea.isOwner(tokenMemberId)
		);
	}
}
