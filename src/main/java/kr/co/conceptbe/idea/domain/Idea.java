package kr.co.conceptbe.idea.domain;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.branch.domain.Branch;
import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
import kr.co.conceptbe.idea.domain.vo.CooperationWay;
import kr.co.conceptbe.idea.domain.vo.IdeaBranches;
import kr.co.conceptbe.idea.domain.vo.IdeaPurposes;
import kr.co.conceptbe.idea.domain.vo.IdeaTeamRecruitments;
import kr.co.conceptbe.idea.domain.vo.Introduce;
import kr.co.conceptbe.idea.domain.vo.Title;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.purpose.domain.Purpose;
import kr.co.conceptbe.teamrecruitment.domain.TeamRecruitment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Idea extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Title title;

    @Embedded
    private Introduce introduce;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CooperationWay cooperationWay;

    @Column(nullable = false)
    private String recruitmentPlace;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member creator;

    @Embedded
    private IdeaBranches branches;

    @Embedded
    private IdeaPurposes purposes;

    @Embedded
    private IdeaTeamRecruitments teamRecruitments;

    @OneToMany(mappedBy = "idea")
    private final List<Hit> hits = new ArrayList<>();

    @OneToMany(mappedBy = "idea")
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "idea", orphanRemoval = true)
    private final List<IdeaLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "idea")
    private final List<Bookmark> bookmarks = new ArrayList<>();

    private Idea(
            Title title,
            Introduce introduce,
            CooperationWay cooperationWay,
            String recruitmentPlace,
            Member creator
    ) {
        this.title = title;
        this.introduce = introduce;
        this.cooperationWay = cooperationWay;
        this.recruitmentPlace = recruitmentPlace;
        this.creator = creator;
    }

    public static Idea of(
            String title,
            String introduce,
            String cooperationWay,
            String recruitmentPlace,
            Member creator,
            List<Branch> branches,
            List<Purpose> purposes,
            List<TeamRecruitment> teamRecruitments
    ) {
        Idea idea = new Idea(
                Title.from(title),
                Introduce.from(introduce),
                CooperationWay.from(cooperationWay),
                recruitmentPlace,
                creator
        );
        idea.branches = IdeaBranches.of(idea, branches);
        idea.purposes = IdeaPurposes.of(idea, purposes);
        idea.teamRecruitments = IdeaTeamRecruitments.of(idea, teamRecruitments);
        return idea;
    }

    public String getTitle() {
        return title.getTitle();
    }

    public String getIntroduce() {
        return introduce.getIntroduce();
    }

    public List<IdeaBranch> getBranches() {
        return branches.getIdeaBranches();
    }

    public List<IdeaPurpose> getPurposes() {
        return purposes.getIdeaPurposes();
    }

    public List<IdeaTeamRecruitment> getTeamRecruitments() {
        return teamRecruitments.getIdeaTeamRecruitments();
    }

    public int getLikesCount() {
        return likes.size();
    }

    public int getHitsCount() {
        return hits.size();
    }

    public int getBookmarksCount() {
        return bookmarks.size();
    }

    public int getCommentsCount() {
        return comments.size();
    }

    public void addComment(Comment comment) { this.comments.add(comment); }

    public void addBookmark(Bookmark bookmark) { this.bookmarks.add(bookmark); }

    public void addIdeaLikes(IdeaLike ideaLike) { this.likes.add(ideaLike); }

    public boolean isMyIdea(Long tokenMemberId) {
        return creator.getId().equals(tokenMemberId);
    }
}
