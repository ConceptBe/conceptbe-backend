package kr.co.conceptbe.idea.domain;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
import kr.co.conceptbe.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class IdeaLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    private IdeaLike(Member member, Idea idea) {
        this.member = member;
        this.idea = idea;
    }

    public static IdeaLike of(Member member, Idea idea) {
        return new IdeaLike(member, idea);
    }

    public static IdeaLike createAssociatedWithIdeaAndMember(Idea idea, Member member) {
        IdeaLike ideaLike = IdeaLike.of(member, idea);
        idea.addIdeaLikes(ideaLike);
        return ideaLike;
    }

    public boolean isOwnerOfLike(Long tokenMemberId) {
        return this.member.getId().equals(tokenMemberId);
    }
}
