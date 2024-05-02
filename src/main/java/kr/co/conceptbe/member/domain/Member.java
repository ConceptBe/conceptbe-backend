package kr.co.conceptbe.member.domain;

import static jakarta.persistence.CascadeType.REMOVE;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;
import kr.co.conceptbe.auth.exception.TokenInvalidException;
import kr.co.conceptbe.bookmark.Bookmark;
import kr.co.conceptbe.comment.CommentLike;
import kr.co.conceptbe.common.entity.base.BaseTimeEntity;
import kr.co.conceptbe.idea.domain.Idea;
import kr.co.conceptbe.member.domain.vo.Nickname;
import kr.co.conceptbe.region.domain.Region;
import kr.co.conceptbe.idea.domain.IdeaLike;
import kr.co.conceptbe.skill.domain.SkillCategory;
import kr.co.conceptbe.skill.domain.SkillLevel;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private OauthId oauthId;

    @Embedded
    private Nickname nickname;

    @Column(nullable = false)
    private String profileImageUrl;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String introduce;

    @Column(nullable = true)
    private String workingPlace;

    @ManyToOne
    @JoinColumn(name = "living_place_id")
    private Region livingPlace;

    @ManyToOne
    @JoinColumn(name = "main_skill_id")
    private SkillCategory mainSkill;

    @OneToMany(mappedBy = "creator", cascade = {CascadeType.PERSIST, REMOVE}, orphanRemoval = true)
    private final List<Idea> createdIdea = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, REMOVE}, orphanRemoval = true)
    private final List<Bookmark> bookmarks = new ArrayList<>();

    @Embedded
    private MemberSkills skills;

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, REMOVE}, orphanRemoval = true)
    private final List<MemberBranch> branches = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, REMOVE}, orphanRemoval = true)
    private final List<MemberPurpose> purposes = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private final List<CommentLike> commentLikes = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private final List<IdeaLike> ideaLikes = new ArrayList<>();

    public Member(
        OauthId oauthId,
        Nickname nickname,
        String profileImageUrl,
        String email,
        String introduce,
        String workingPlace,
        Region livingPlace
    ) {
        this.oauthId = oauthId;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.email = email;
        this.introduce = introduce;
        this.workingPlace = workingPlace;
        this.livingPlace = livingPlace;
    }

    public static void validateMember(Long tokenMemberId, Long validateId) {
        if(tokenMemberId.equals(validateId)) {
            return;
        }
        throw new TokenInvalidException();
    }

    public void updateMainSkill(SkillCategory mainSkill) {
        this.mainSkill = mainSkill;
    }

    public void addSkills(Member member, List<SkillCategory> skillCategories, List<SkillLevel> skillLevels) {
        this.skills = MemberSkills.of(member, skillCategories, skillLevels);
    }

    public void addPurpose(MemberPurpose memberPurpose) {
        this.purposes.add(memberPurpose);
    }


    public void updateProfile(
        String nickname,
        SkillCategory mainSkill,
        String profileImageUrl,
        Region livingPlace,
        String from,
        String introduction
    ) {
        this.nickname = Nickname.from(nickname);
        this.mainSkill = mainSkill;
        this.profileImageUrl = profileImageUrl;
        this.livingPlace = livingPlace;
        this.workingPlace = from;
        this.introduce = introduction;
    }

    public void updateSkills(Member member, List<SkillCategory> skillCategories,
        List<SkillLevel> skillLevels) {
        this.skills.clear();
        this.skills.addSkills(member, skillCategories, skillLevels);
    }

    public void updateJoinPurposes(List<MemberPurpose> memberPurposes) {
        this.purposes.clear();
        this.purposes.addAll(memberPurposes);
    }

    public String getNickname() {
        return nickname.getNickname();
    }
}
