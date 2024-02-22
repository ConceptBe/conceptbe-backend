package kr.co.conceptbe.bookmark;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookmark is a Querydsl query type for Bookmark
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookmark extends EntityPathBase<Bookmark> {

    private static final long serialVersionUID = -1332145296L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookmark bookmark = new QBookmark("bookmark");

    public final kr.co.conceptbe.common.entity.base.QBaseTimeEntity _super = new kr.co.conceptbe.common.entity.base.QBaseTimeEntity(this);

    public final QBookmarkID bookmarkID;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final kr.co.conceptbe.idea.domain.QIdea idea;

    public final kr.co.conceptbe.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBookmark(String variable) {
        this(Bookmark.class, forVariable(variable), INITS);
    }

    public QBookmark(Path<? extends Bookmark> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookmark(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookmark(PathMetadata metadata, PathInits inits) {
        this(Bookmark.class, metadata, inits);
    }

    public QBookmark(Class<? extends Bookmark> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bookmarkID = inits.isInitialized("bookmarkID") ? new QBookmarkID(forProperty("bookmarkID")) : null;
        this.idea = inits.isInitialized("idea") ? new kr.co.conceptbe.idea.domain.QIdea(forProperty("idea"), inits.get("idea")) : null;
        this.member = inits.isInitialized("member") ? new kr.co.conceptbe.member.domain.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

