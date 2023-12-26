package kr.co.conceptbe.member;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "member",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "oauth_id_unique",
            columnNames = {
                "oauth_server_id",
                "oauth_server_type"
            }
        ),
    }
)
public class Member extends BaseTimeEntity {

    //TODO 패키지 변경

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OauthId oauthId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = true)
    private String introduce;

    @Column(nullable = true)
    private String workingPlace;

    @Column(nullable = true)
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
            String nickname,
            String imageUrl,
            String introduce,
            String workingPlace,
            String livingPlace
    ) {
        this.id = id;
        this.oauthId = oauthId;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.introduce = introduce;
        this.workingPlace = workingPlace;
        this.livingPlace = livingPlace;
    }
}
