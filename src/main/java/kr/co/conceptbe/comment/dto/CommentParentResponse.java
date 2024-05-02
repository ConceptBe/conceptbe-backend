package kr.co.conceptbe.comment.dto;

import java.time.LocalDateTime;
import java.util.List;
import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.member.domain.Member;

public record CommentParentResponse (
	Long memberId,
	Long parentCommentId,
	String nickname,
	String profileImageUrl,
	String memberMainSkill,
	LocalDateTime createdAt,
	String content,
	Integer likesCount,
	Integer commentCount,
	List<CommentChildResponse> commentChildResponses,
	Boolean owner,
	Boolean deleted,
	Boolean likes
) {
	public static CommentParentResponse of(Comment comment, Long tokenMemberId) {
		return new CommentParentResponse(
			getId(comment),
			comment.getId(),
			getNickname(comment),
			getProfileImageUrl(comment),
			getMemberSkills(comment),
			comment.getCreatedAt(),
			comment.getContent(),
			comment.getLikesCount(),
			comment.getCommentsCount(),
			comment.getComments()
				.stream()
				.map(commentParent -> CommentChildResponse.of(commentParent, tokenMemberId))
				.toList(),
			isOwner(comment, tokenMemberId),
			comment.getDeleted(),
			comment.isLike(tokenMemberId));
	}

	private static boolean isOwner(Comment comment, Long tokenMemberId) {
		if (comment.getCreator() == null) {
			return false;
		}
		return comment.isOwner(tokenMemberId);
	}

	private static Long getId(Comment comment) {
		Member creator = comment.getCreator();
		if (creator == null) {
			return null;
		}
		return creator.getId();
	}

	private static String getNickname(Comment comment) {
		Member creator = comment.getCreator();
		if (creator == null) {
			return null;
		}
		return creator.getNickname();
	}

	private static String getProfileImageUrl(Comment comment) {
		Member creator = comment.getCreator();
		if (creator == null) {
			return null;
		}
		return creator.getProfileImageUrl();
	}

	private static String getMemberSkills(Comment comment) {
		Member creator = comment.getCreator();
		if (creator == null) {
			return null;
		}
		return comment.getCreator().getMainSkill().getName();
	}
}
