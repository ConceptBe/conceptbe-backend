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
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
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

    @Column
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

    @OneToMany(mappedBy = "member")
    private final List<MemberSkillCategory> skills = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<MemberBranch> branches = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private final List<MemberPurpose> purposes = new ArrayList<>();

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
