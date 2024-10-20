package kr.co.conceptbe.comment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Comment parentComment;

    @Column
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idea_id")
    private Idea idea;

    @OneToMany(mappedBy = "comment", orphanRemoval = true, cascade = {CascadeType.REMOVE})
    private final List<CommentLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "parentComment")
    private final List<Comment> comments = new ArrayList<>();

    public Comment(String content, Comment parentComment, Member creator, Idea idea) {
        this.content = content;
        this.parentComment = parentComment;
        this.creator = creator;
        this.idea = idea;
        this.deleted = false;
    }

    public static Comment createCommentAssociatedWithIdeaAndCreator(
        String content,
        Comment parentComment,
        Idea idea,
        Member creator
    ) {
        Comment comment = new Comment(content, parentComment, creator, idea);
        idea.addComment(comment);
        return comment;
    }

    public void addParentComment(Comment comment) {
        this.parentComment = comment;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addCommentLike(CommentLike commentLike) {
        this.likes.add(commentLike);
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void commentDelete() {
        this.deleted = true;
    }

    public int getLikesCount() {
        return likes.size();
    }

    public int getCommentsCount() {
        return comments.size();
    }

    public boolean isParentComment() {
        return this.parentComment == null;
    }

    public boolean isOwner(Long tokenMemberId) {
        return creator.getId().equals(tokenMemberId);
    }

    public boolean isLike(Long memberId) {
        return likes.stream()
            .anyMatch(like -> like.isLike(memberId));
    }

    public void deleteWithMember() {
        this.deleted = true;
        this.creator = null;
    }
}
