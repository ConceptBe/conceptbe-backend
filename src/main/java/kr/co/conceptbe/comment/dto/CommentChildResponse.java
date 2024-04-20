package kr.co.conceptbe.comment.dto;

import java.time.LocalDateTime;
import kr.co.conceptbe.comment.Comment;

public record CommentChildResponse(
    Long memberId,
    Long childCommentId,
    String nickname,
    String profileImageUrl,
    String mainSkill,
    LocalDateTime createdAt,
    String content,
    Integer likesCount,
    Boolean owner,
    Boolean deleted,
    Boolean likes
) {

    public static CommentChildResponse of(Comment comment, Long tokenMemberId) {
        return new CommentChildResponse(
            comment.getCreator().getId(),
            comment.getId(),
            comment.getCreator().getNickname(),
            comment.getCreator().getProfileImageUrl(),
            comment.getCreator().getMainSkill().getName(),
            comment.getCreatedAt(),
            comment.getContent(),
            comment.getLikesCount(),
            comment.isOwner(tokenMemberId),
            comment.getDeleted(),
            comment.isLike(tokenMemberId)
        );
    }
}
