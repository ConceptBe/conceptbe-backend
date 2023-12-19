package kr.co.conceptbe.idea;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.comment.Comment;
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
import kr.co.conceptbe.common.entity.Branch;
import kr.co.conceptbe.common.entity.Purpose;
import kr.co.conceptbe.common.entity.TeamRecruitment;
import kr.co.conceptbe.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Idea extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String title;

    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false)
    private String cooperationWay;

    @Column(nullable = false)
    private String recruitmentPlace;

    @Column(nullable = false)
    @ColumnDefault(value = "0")
    private Long hit = 0L;

    @OneToMany(mappedBy = "idea")
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "idea")
    private final List<IdeaLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "idea")
    private final List<Bookmark> bookmarks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member creator;

    @ManyToMany
    @JoinTable(
            name = "IDEA_BRANCH",
            joinColumns = @JoinColumn(name = "IDEA_ID"),
            inverseJoinColumns = @JoinColumn(name = "BRANCH_ID")
    )
    private final List<Branch> branches = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "IDEA_PURPOSE",
            joinColumns = @JoinColumn(name = "IDEA_ID"),
            inverseJoinColumns = @JoinColumn(name = "PURPOSE_ID")
    )
    private final List<Purpose> purposes = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "IDEA_TEAM_RECRUITMENT",
            joinColumns = @JoinColumn(name = "IDEA_ID"),
            inverseJoinColumns = @JoinColumn(name = "TEAM_RECRUITMENT_ID")
    )
    private final List<TeamRecruitment> teamRecruitments = new ArrayList<>();

    public Idea(
            Long id,
            Member creator,
            String title,
            String introduce,
            String cooperationWay,
            String recruitmentPlace,
            Long hit
    ) {
        this.id = id;
        this.creator = creator;
        this.title = title;
        this.introduce = introduce;
        this.cooperationWay = cooperationWay;
        this.recruitmentPlace = recruitmentPlace;
        this.hit = hit;
    }

}
