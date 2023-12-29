package kr.co.conceptbe.idea;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
import kr.co.conceptbe.idea.vo.IdeaBranches;
import kr.co.conceptbe.idea.vo.IdeaPurposes;
import kr.co.conceptbe.idea.vo.IdeaTeamRecruitments;
import kr.co.conceptbe.idea.vo.Introduce;
import kr.co.conceptbe.idea.vo.Title;
import kr.co.conceptbe.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Idea extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Title title;

    @Embedded
    private Introduce introduce;

    @Column(nullable = false)
    private String cooperationWay;

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
    private List<Hit> hits = new ArrayList<>();

    @OneToMany(mappedBy = "idea")
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "idea")
    private final List<IdeaLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "idea")
    private final List<Bookmark> bookmarks = new ArrayList<>();

    public Idea(
            Title title,
            Introduce introduce,
            String cooperationWay,
            String recruitmentPlace,
            Member creator,
            List<Hit> hits
    ) {
        this.title = title;
        this.introduce = introduce;
        this.cooperationWay = cooperationWay;
        this.recruitmentPlace = recruitmentPlace;
        this.creator = creator;
        this.hits = hits;
    }

}
