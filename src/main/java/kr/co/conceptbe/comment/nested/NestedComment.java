package kr.co.conceptbe.comment.nested;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
import kr.co.conceptbe.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class NestedComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member creator;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "nestedComment")
    private List<NestedCommentLike> nestedCommentLikes = new ArrayList<>();

    public NestedComment(
            Long id,
            String content,
            Member creator,
            Comment parentComment,
            List<NestedCommentLike> nestedCommentLikes
    ) {
        this.id = id;
        this.content = content;
        this.creator = creator;
        this.parentComment = parentComment;
        this.nestedCommentLikes = nestedCommentLikes;
    }

}
