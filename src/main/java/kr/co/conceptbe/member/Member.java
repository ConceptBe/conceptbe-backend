package kr.co.conceptbe.member;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
import kr.co.conceptbe.common.entity.Branch;
import kr.co.conceptbe.common.entity.Purpose;
import kr.co.conceptbe.common.entity.Skill;
import kr.co.conceptbe.idea.Idea;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OauthId oauthId;

    @Column(nullable = true)
    private String imageUrl;

    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false)
    private String workingPlace;

    @Column(nullable = false)
    private String livingPlace;

    @OneToMany(mappedBy = "creator")
    private final List<Idea> createdIdea = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<Bookmark> bookmarks = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "USER_SKILL",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "SKILL_ID")
    )
    private final List<Skill> skills = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "USER_BRANCH",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "BRANCH_ID")
    )
    private final List<Branch> branches = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "USER_PURPOSE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PURPOSE_ID")
    )
    private final List<Purpose> purposes = new ArrayList<>();

    public Member(
            Long id,
            OauthId oauthId,
            String imageUrl,
            String introduce,
            String workingPlace,
            String livingPlace
    ) {
        this.id = id;
        this.oauthId = oauthId;
        this.imageUrl = imageUrl;
        this.introduce = introduce;
        this.workingPlace = workingPlace;
        this.livingPlace = livingPlace;
    }

    @Embeddable
    @NoArgsConstructor(access = PROTECTED)
    @Getter
    static class OauthId {

        @Column(nullable = false)
        private Long oauthServerId;

        @Enumerated(EnumType.STRING)
        private OauthServerType oauthServerType;

        public OauthId(Long oauthServerId, OauthServerType oauthServerType) {
            this.oauthServerId = oauthServerId;
            this.oauthServerType = oauthServerType;
        }

        public enum OauthServerType {
            KAKAO, NAVER
        }

    }

}
