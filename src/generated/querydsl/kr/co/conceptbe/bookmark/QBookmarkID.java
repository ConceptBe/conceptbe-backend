package kr.co.conceptbe.bookmark;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookmarkID is a Querydsl query type for BookmarkID
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBookmarkID extends BeanPath<BookmarkID> {

    private static final long serialVersionUID = -291372917L;

    public static final QBookmarkID bookmarkID = new QBookmarkID("bookmarkID");

    public final NumberPath<Long> ideaId = createNumber("ideaId", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public QBookmarkID(String variable) {
        super(BookmarkID.class, forVariable(variable));
    }

    public QBookmarkID(Path<? extends BookmarkID> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookmarkID(PathMetadata metadata) {
        super(BookmarkID.class, metadata);
    }

}

