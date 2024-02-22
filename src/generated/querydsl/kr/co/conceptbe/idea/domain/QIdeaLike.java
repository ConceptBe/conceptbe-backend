package kr.co.conceptbe.idea.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIdeaLike is a Querydsl query type for IdeaLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIdeaLike extends EntityPathBase<IdeaLike> {

    private static final long serialVersionUID = 1306806431L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIdeaLike ideaLike = new QIdeaLike("ideaLike");

    public final kr.co.conceptbe.common.entity.base.QBaseTimeEntity _super = new kr.co.conceptbe.common.entity.base.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QIdea idea;

    public final QIdeaLikeID ideaLikeID;

    public final kr.co.conceptbe.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QIdeaLike(String variable) {
        this(IdeaLike.class, forVariable(variable), INITS);
    }

    public QIdeaLike(Path<? extends IdeaLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIdeaLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIdeaLike(PathMetadata metadata, PathInits inits) {
        this(IdeaLike.class, metadata, inits);
    }

    public QIdeaLike(Class<? extends IdeaLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.idea = inits.isInitialized("idea") ? new QIdea(forProperty("idea"), inits.get("idea")) : null;
        this.ideaLikeID = inits.isInitialized("ideaLikeID") ? new QIdeaLikeID(forProperty("ideaLikeID")) : null;
        this.member = inits.isInitialized("member") ? new kr.co.conceptbe.member.domain.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

