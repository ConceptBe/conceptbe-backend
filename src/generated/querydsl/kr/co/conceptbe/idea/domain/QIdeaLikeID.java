package kr.co.conceptbe.idea.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIdeaLikeID is a Querydsl query type for IdeaLikeID
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QIdeaLikeID extends BeanPath<IdeaLikeID> {

    private static final long serialVersionUID = 1710532090L;

    public static final QIdeaLikeID ideaLikeID = new QIdeaLikeID("ideaLikeID");

    public final NumberPath<Long> ideaId = createNumber("ideaId", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public QIdeaLikeID(String variable) {
        super(IdeaLikeID.class, forVariable(variable));
    }

    public QIdeaLikeID(Path<? extends IdeaLikeID> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIdeaLikeID(PathMetadata metadata) {
        super(IdeaLikeID.class, metadata);
    }

}

