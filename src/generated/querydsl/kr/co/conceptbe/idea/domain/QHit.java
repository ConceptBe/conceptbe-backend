package kr.co.conceptbe.idea.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHit is a Querydsl query type for Hit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHit extends EntityPathBase<Hit> {

    private static final long serialVersionUID = -1125615742L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHit hit = new QHit("hit");

    public final kr.co.conceptbe.common.entity.base.QBaseTimeEntity _super = new kr.co.conceptbe.common.entity.base.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QIdea idea;

    public final kr.co.conceptbe.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QHit(String variable) {
        this(Hit.class, forVariable(variable), INITS);
    }

    public QHit(Path<? extends Hit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHit(PathMetadata metadata, PathInits inits) {
        this(Hit.class, metadata, inits);
    }

    public QHit(Class<? extends Hit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.idea = inits.isInitialized("idea") ? new QIdea(forProperty("idea"), inits.get("idea")) : null;
        this.member = inits.isInitialized("member") ? new kr.co.conceptbe.member.domain.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

