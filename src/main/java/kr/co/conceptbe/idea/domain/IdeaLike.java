package kr.co.conceptbe.idea.domain;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import java.io.Serializable;
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
import kr.co.conceptbe.member.domain.Member;
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

    private IdeaLike(IdeaLikeID ideaLikeID, Member member, Idea idea) {
        this.ideaLikeID = ideaLikeID;
        this.member = member;
        this.idea = idea;
    }

    public static IdeaLike of(IdeaLikeID ideaLikeID, Member member, Idea idea) {
        return new IdeaLike(ideaLikeID, member, idea);
    }

    public static IdeaLike createIdeaLikeAssociatedWithIdeaAndMember(Idea idea, Member member) {
        IdeaLikeID ideaLikeID = IdeaLikeID.of(member, idea);
        IdeaLike ideaLike = IdeaLike.of(ideaLikeID, member, idea);
        idea.addIdeaLikes(ideaLike);
        return ideaLike;
    }

    public boolean isOwnerOfLike(Long tokenMemberId) {
        return this.member.getId().equals(tokenMemberId);
    }
}
