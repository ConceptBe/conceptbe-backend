package kr.co.conceptbe.comment;

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
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
import kr.co.conceptbe.idea.Idea;
import kr.co.conceptbe.member.Member;
import kr.co.conceptbe.comment.nested.NestedComment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "member_id")
    private Member creator;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    @OneToMany(mappedBy = "comment")
    private List<CommentLike> commentLikes = new ArrayList<>();

    @OneToMany(mappedBy = "parentComment")
    private List<NestedComment> nestedComments = new ArrayList<>();

}
