package kr.co.conceptbe.idea.domain;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.CascadeType;
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
import kr.co.conceptbe.idea.domain.vo.IdeaSkillCategories;
import kr.co.conceptbe.idea.domain.vo.Introduce;
import kr.co.conceptbe.idea.domain.vo.Title;
import kr.co.conceptbe.member.domain.Member;
import kr.co.conceptbe.purpose.domain.Purpose;
import kr.co.conceptbe.region.domain.Region;
import kr.co.conceptbe.skill.domain.SkillCategory;
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

    @ManyToOne
    @JoinColumn(name = "recruitment_place")
    private Region recruitmentPlace;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member creator;

    @Embedded
    private IdeaBranches branches;

    @Embedded
    private IdeaPurposes purposes;

    @Embedded
    private IdeaSkillCategories skillCategories;

    @OneToMany(mappedBy = "idea", orphanRemoval = true, cascade = {CascadeType.REMOVE})
    private final List<Hit> hits = new ArrayList<>();

    @OneToMany(mappedBy = "idea", orphanRemoval = true, cascade = {CascadeType.REMOVE})
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "idea", orphanRemoval = true, cascade = {CascadeType.REMOVE})
    private final List<IdeaLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "idea", orphanRemoval = true, cascade = {CascadeType.REMOVE})
    private final List<Bookmark> bookmarks = new ArrayList<>();

    private Idea(
        Title title,
        Introduce introduce,
        CooperationWay cooperationWay,
        Region recruitmentPlace,
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
        Region recruitmentPlace,
        Member creator,
        List<Branch> branches,
        List<Purpose> purposes,
        List<SkillCategory> skillCategories
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
        idea.skillCategories = IdeaSkillCategories.of(idea, skillCategories);
        return idea;
    }

    public void update(
        String title,
        String introduce,
        String cooperationWay,
        Region recruitmentPlace,
        List<Branch> branches,
        List<Purpose> purposes,
        List<SkillCategory> skillCategories
    ) {
        this.title = Title.from(title);
        this.introduce = Introduce.from(introduce);
        this.cooperationWay = CooperationWay.from(cooperationWay);
        this.recruitmentPlace = recruitmentPlace;
        this.branches.update(this, branches);
        this.purposes.update(this, purposes);
        this.skillCategories.update(this, skillCategories);
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

    public String getCooperationWay() {
        return cooperationWay.getValue();
    }

    public String getRecruitmentPlace() {
        return recruitmentPlace.getName();
    }

    public List<IdeaSkillCategory> getSkillCategories() {
        return skillCategories.getIdeaSkillCategories();
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

    public void addHit(Hit hit) {
        this.hits.add(hit);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addBookmark(Bookmark bookmark) {
        this.bookmarks.add(bookmark);
    }

    public void addIdeaLikes(IdeaLike ideaLike) {
        this.likes.add(ideaLike);
    }

    public boolean isOwner(Long memberId) {
        return creator.getId().equals(memberId);
    }

    public boolean isOwnerLike(Long memberId) {
        return likes.stream()
            .anyMatch(like -> like.isOwnerOfLike(memberId));
    }

    public boolean isOwnerScrap(Long memberId) {
        return bookmarks.stream()
            .anyMatch(bookmark -> bookmark.isOwnerOfBookmark(memberId));
    }
}
