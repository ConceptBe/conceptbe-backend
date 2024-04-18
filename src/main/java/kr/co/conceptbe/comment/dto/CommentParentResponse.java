package kr.co.conceptbe.comment.dto;

import java.time.LocalDateTime;
import java.util.List;
import kr.co.conceptbe.comment.Comment;

public record CommentParentResponse(
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
            comment.getCreator().getId(),
            comment.getId(),
            comment.getCreator().getNickname(),
            comment.getCreator().getProfileImageUrl(),
            comment.getCreator().getMainSkill().getName(),
            comment.getCreatedAt(),
            comment.getContent(),
            comment.getLikesCount(),
            comment.getCommentsCount(),
            comment.getComments()
                .stream()
                .map(commentParent -> CommentChildResponse.of(commentParent, tokenMemberId))
                .toList(),
            comment.isOwner(tokenMemberId),
            comment.getDeleted(),
            comment.isLike(tokenMemberId));
    }
}
