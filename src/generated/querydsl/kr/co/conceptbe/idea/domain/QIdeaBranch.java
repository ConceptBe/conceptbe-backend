package kr.co.conceptbe.idea.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIdeaBranch is a Querydsl query type for IdeaBranch
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIdeaBranch extends EntityPathBase<IdeaBranch> {

    private static final long serialVersionUID = 1432263850L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIdeaBranch ideaBranch = new QIdeaBranch("ideaBranch");

    public final kr.co.conceptbe.branch.domain.QBranch branch;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QIdea idea;

    public QIdeaBranch(String variable) {
        this(IdeaBranch.class, forVariable(variable), INITS);
    }

    public QIdeaBranch(Path<? extends IdeaBranch> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIdeaBranch(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIdeaBranch(PathMetadata metadata, PathInits inits) {
        this(IdeaBranch.class, metadata, inits);
    }

    public QIdeaBranch(Class<? extends IdeaBranch> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branch = inits.isInitialized("branch") ? new kr.co.conceptbe.branch.domain.QBranch(forProperty("branch")) : null;
        this.idea = inits.isInitialized("idea") ? new QIdea(forProperty("idea"), inits.get("idea")) : null;
    }

}

