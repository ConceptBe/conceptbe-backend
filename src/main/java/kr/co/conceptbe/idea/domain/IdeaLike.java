package kr.co.conceptbe.idea.domain;

import static lombok.AccessLevel.PROTECTED;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
import kr.co.conceptbe.idea.IdeaLikeID;
import kr.co.conceptbe.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class IdeaLike extends BaseTimeEntity implements Serializable {

    @EmbeddedId
    private IdeaLikeID ideaLikeID;

    @MapsId("memberId")
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @MapsId("ideaId")
    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    public IdeaLike(Member member, Idea idea) {
        this.member = member;
        this.idea = idea;
    }
}
