package kr.co.conceptbe.idea.domain.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIdeaBranches is a Querydsl query type for IdeaBranches
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QIdeaBranches extends BeanPath<IdeaBranches> {

    private static final long serialVersionUID = -864046933L;

    public static final QIdeaBranches ideaBranches1 = new QIdeaBranches("ideaBranches1");

    public final ListPath<kr.co.conceptbe.idea.domain.IdeaBranch, kr.co.conceptbe.idea.domain.QIdeaBranch> ideaBranches = this.<kr.co.conceptbe.idea.domain.IdeaBranch, kr.co.conceptbe.idea.domain.QIdeaBranch>createList("ideaBranches", kr.co.conceptbe.idea.domain.IdeaBranch.class, kr.co.conceptbe.idea.domain.QIdeaBranch.class, PathInits.DIRECT2);

    public QIdeaBranches(String variable) {
        super(IdeaBranches.class, forVariable(variable));
    }

    public QIdeaBranches(Path<? extends IdeaBranches> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIdeaBranches(PathMetadata metadata) {
        super(IdeaBranches.class, metadata);
    }

}

