package kr.co.conceptbe.comment.nested;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.co.conceptbe.comment.nested.NestedComment;
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
import kr.co.conceptbe.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class NestedCommentLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "nested_comment_id")
    private NestedComment nestedComment;

    public NestedCommentLike(Long id, Member member, NestedComment nestedComment) {
        this.id = id;
        this.member = member;
        this.nestedComment = nestedComment;
    }

}
