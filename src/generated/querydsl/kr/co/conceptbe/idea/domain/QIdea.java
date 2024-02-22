package kr.co.conceptbe.idea.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIdea is a Querydsl query type for Idea
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIdea extends EntityPathBase<Idea> {

    private static final long serialVersionUID = -534325016L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIdea idea = new QIdea("idea");

    public final kr.co.conceptbe.common.entity.base.QBaseTimeEntity _super = new kr.co.conceptbe.common.entity.base.QBaseTimeEntity(this);

    public final ListPath<kr.co.conceptbe.bookmark.Bookmark, kr.co.conceptbe.bookmark.QBookmark> bookmarks = this.<kr.co.conceptbe.bookmark.Bookmark, kr.co.conceptbe.bookmark.QBookmark>createList("bookmarks", kr.co.conceptbe.bookmark.Bookmark.class, kr.co.conceptbe.bookmark.QBookmark.class, PathInits.DIRECT2);

    public final kr.co.conceptbe.idea.domain.vo.QIdeaBranches branches;

    public final ListPath<kr.co.conceptbe.comment.Comment, kr.co.conceptbe.comment.QComment> comments = this.<kr.co.conceptbe.comment.Comment, kr.co.conceptbe.comment.QComment>createList("comments", kr.co.conceptbe.comment.Comment.class, kr.co.conceptbe.comment.QComment.class, PathInits.DIRECT2);

    public final EnumPath<kr.co.conceptbe.idea.domain.vo.CooperationWay> cooperationWay = createEnum("cooperationWay", kr.co.conceptbe.idea.domain.vo.CooperationWay.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final kr.co.conceptbe.member.domain.QMember creator;

    public final ListPath<Hit, QHit> hits = this.<Hit, QHit>createList("hits", Hit.class, QHit.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final kr.co.conceptbe.idea.domain.vo.QIdeaSkillCategories ideaSkillCategories;

    public final kr.co.conceptbe.idea.domain.vo.QIntroduce introduce;

    public final ListPath<IdeaLike, QIdeaLike> likes = this.<IdeaLike, QIdeaLike>createList("likes", IdeaLike.class, QIdeaLike.class, PathInits.DIRECT2);

    public final kr.co.conceptbe.idea.domain.vo.QIdeaPurposes purposes;

    public final kr.co.conceptbe.region.domain.QRegion recruitmentPlace;

    public final kr.co.conceptbe.idea.domain.vo.QTitle title;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QIdea(String variable) {
        this(Idea.class, forVariable(variable), INITS);
    }

    public QIdea(Path<? extends Idea> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIdea(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIdea(PathMetadata metadata, PathInits inits) {
        this(Idea.class, metadata, inits);
    }

    public QIdea(Class<? extends Idea> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branches = inits.isInitialized("branches") ? new kr.co.conceptbe.idea.domain.vo.QIdeaBranches(forProperty("branches")) : null;
        this.creator = inits.isInitialized("creator") ? new kr.co.conceptbe.member.domain.QMember(forProperty("creator"), inits.get("creator")) : null;
        this.ideaSkillCategories = inits.isInitialized("ideaSkillCategories") ? new kr.co.conceptbe.idea.domain.vo.QIdeaSkillCategories(forProperty("ideaSkillCategories")) : null;
        this.introduce = inits.isInitialized("introduce") ? new kr.co.conceptbe.idea.domain.vo.QIntroduce(forProperty("introduce")) : null;
        this.purposes = inits.isInitialized("purposes") ? new kr.co.conceptbe.idea.domain.vo.QIdeaPurposes(forProperty("purposes")) : null;
        this.recruitmentPlace = inits.isInitialized("recruitmentPlace") ? new kr.co.conceptbe.region.domain.QRegion(forProperty("recruitmentPlace")) : null;
        this.title = inits.isInitialized("title") ? new kr.co.conceptbe.idea.domain.vo.QTitle(forProperty("title")) : null;
    }

}

