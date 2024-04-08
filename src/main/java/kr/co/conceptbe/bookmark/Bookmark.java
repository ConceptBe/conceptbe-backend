package kr.co.conceptbe.bookmark;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import java.io.Serializable;
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Bookmark extends BaseTimeEntity {

    @EmbeddedId
    private BookmarkID bookmarkID;

    @MapsId("memberId")
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @MapsId("ideaId")
    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    private Bookmark(BookmarkID bookmarkID, Member member, Idea idea) {
        this.bookmarkID = bookmarkID;
        this.member = member;
        this.idea = idea;
    }

    public static Bookmark of(BookmarkID bookmarkID, Member member, Idea idea) {
        Bookmark bookmark = new Bookmark(bookmarkID, member, idea);
        idea.addBookmark(bookmark);
        return bookmark;
    }

    public static Bookmark createBookmarkAssociatedWithIdeaAndMember(Idea idea, Member member) {
        BookmarkID bookmarkID = BookmarkID.of(member.getId(), idea.getId());
        Bookmark bookmark = new Bookmark(bookmarkID, member, idea);
        idea.addBookmark(bookmark);
        return bookmark;
    }

    public boolean isOwnerOfBookmark(Long tokenMemberId) {
        return this.member.getId().equals(tokenMemberId);
    }
}
