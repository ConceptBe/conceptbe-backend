package kr.co.conceptbe.idea.domain;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDate;

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
public class Hit extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    public Hit(Member member, Idea idea) {
        this.member = member;
        this.idea = idea;
    }

    public static Hit ofIdeaAndMember(Idea idea, Member member) {
        Hit hit = new Hit(member, idea);
        idea.addHit(hit);
        return hit;
    }

    public boolean isBeforeLocalDate() {
        return this.getCreatedAt().toLocalDate().isBefore(LocalDate.now());
    }
}
