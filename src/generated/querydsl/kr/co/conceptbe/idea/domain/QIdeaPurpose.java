package kr.co.conceptbe.idea.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIdeaPurpose is a Querydsl query type for IdeaPurpose
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIdeaPurpose extends EntityPathBase<IdeaPurpose> {

    private static final long serialVersionUID = 1092314902L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIdeaPurpose ideaPurpose = new QIdeaPurpose("ideaPurpose");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QIdea idea;

    public final kr.co.conceptbe.purpose.domain.QPurpose purpose;

    public QIdeaPurpose(String variable) {
        this(IdeaPurpose.class, forVariable(variable), INITS);
    }

    public QIdeaPurpose(Path<? extends IdeaPurpose> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIdeaPurpose(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIdeaPurpose(PathMetadata metadata, PathInits inits) {
        this(IdeaPurpose.class, metadata, inits);
    }

    public QIdeaPurpose(Class<? extends IdeaPurpose> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.idea = inits.isInitialized("idea") ? new QIdea(forProperty("idea"), inits.get("idea")) : null;
        this.purpose = inits.isInitialized("purpose") ? new kr.co.conceptbe.purpose.domain.QPurpose(forProperty("purpose")) : null;
    }

}

