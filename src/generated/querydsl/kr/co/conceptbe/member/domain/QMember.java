package kr.co.conceptbe.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 157065006L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final kr.co.conceptbe.common.entity.base.QBaseTimeEntity _super = new kr.co.conceptbe.common.entity.base.QBaseTimeEntity(this);

    public final ListPath<kr.co.conceptbe.bookmark.Bookmark, kr.co.conceptbe.bookmark.QBookmark> bookmarks = this.<kr.co.conceptbe.bookmark.Bookmark, kr.co.conceptbe.bookmark.QBookmark>createList("bookmarks", kr.co.conceptbe.bookmark.Bookmark.class, kr.co.conceptbe.bookmark.QBookmark.class, PathInits.DIRECT2);

    public final ListPath<MemberBranch, QMemberBranch> branches = this.<MemberBranch, QMemberBranch>createList("branches", MemberBranch.class, QMemberBranch.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ListPath<kr.co.conceptbe.idea.domain.Idea, kr.co.conceptbe.idea.domain.QIdea> createdIdea = this.<kr.co.conceptbe.idea.domain.Idea, kr.co.conceptbe.idea.domain.QIdea>createList("createdIdea", kr.co.conceptbe.idea.domain.Idea.class, kr.co.conceptbe.idea.domain.QIdea.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduce = createString("introduce");

    public final EnumPath<Region> livingPlace = createEnum("livingPlace", Region.class);

    public final kr.co.conceptbe.skill.domain.QSkillCategory mainSkill;

    public final StringPath nickname = createString("nickname");

    public final QOauthId oauthId;

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final ListPath<MemberPurpose, QMemberPurpose> purposes = this.<MemberPurpose, QMemberPurpose>createList("purposes", MemberPurpose.class, QMemberPurpose.class, PathInits.DIRECT2);

    public final ListPath<MemberSkillCategory, QMemberSkillCategory> skills = this.<MemberSkillCategory, QMemberSkillCategory>createList("skills", MemberSkillCategory.class, QMemberSkillCategory.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath workingPlace = createString("workingPlace");

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mainSkill = inits.isInitialized("mainSkill") ? new kr.co.conceptbe.skill.domain.QSkillCategory(forProperty("mainSkill"), inits.get("mainSkill")) : null;
        this.oauthId = inits.isInitialized("oauthId") ? new QOauthId(forProperty("oauthId")) : null;
    }

}

