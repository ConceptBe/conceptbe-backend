package kr.co.conceptbe.idea.domain.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIdeaPurposes is a Querydsl query type for IdeaPurposes
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QIdeaPurposes extends BeanPath<IdeaPurposes> {

    private static final long serialVersionUID = 916915696L;

    public static final QIdeaPurposes ideaPurposes1 = new QIdeaPurposes("ideaPurposes1");

    public final ListPath<kr.co.conceptbe.idea.domain.IdeaPurpose, kr.co.conceptbe.idea.domain.QIdeaPurpose> ideaPurposes = this.<kr.co.conceptbe.idea.domain.IdeaPurpose, kr.co.conceptbe.idea.domain.QIdeaPurpose>createList("ideaPurposes", kr.co.conceptbe.idea.domain.IdeaPurpose.class, kr.co.conceptbe.idea.domain.QIdeaPurpose.class, PathInits.DIRECT2);

    public QIdeaPurposes(String variable) {
        super(IdeaPurposes.class, forVariable(variable));
    }

    public QIdeaPurposes(Path<? extends IdeaPurposes> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIdeaPurposes(PathMetadata metadata) {
        super(IdeaPurposes.class, metadata);
    }

}

