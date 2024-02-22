package kr.co.conceptbe.idea.domain.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIntroduce is a Querydsl query type for Introduce
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QIntroduce extends BeanPath<Introduce> {

    private static final long serialVersionUID = 932674779L;

    public static final QIntroduce introduce1 = new QIntroduce("introduce1");

    public final StringPath introduce = createString("introduce");

    public QIntroduce(String variable) {
        super(Introduce.class, forVariable(variable));
    }

    public QIntroduce(Path<? extends Introduce> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIntroduce(PathMetadata metadata) {
        super(Introduce.class, metadata);
    }

}

