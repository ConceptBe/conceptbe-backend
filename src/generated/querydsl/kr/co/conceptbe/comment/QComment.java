package kr.co.conceptbe.comment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = -621435246L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment comment = new QComment("comment");

    public final kr.co.conceptbe.common.entity.base.QBaseTimeEntity _super = new kr.co.conceptbe.common.entity.base.QBaseTimeEntity(this);

    public final ListPath<Comment, QComment> comments = this.<Comment, QComment>createList("comments", Comment.class, QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final kr.co.conceptbe.member.domain.QMember creator;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final kr.co.conceptbe.idea.domain.QIdea idea;

    public final ListPath<CommentLike, QCommentLike> likes = this.<CommentLike, QCommentLike>createList("likes", CommentLike.class, QCommentLike.class, PathInits.DIRECT2);

    public final QComment parentComment;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QComment(String variable) {
        this(Comment.class, forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.creator = inits.isInitialized("creator") ? new kr.co.conceptbe.member.domain.QMember(forProperty("creator"), inits.get("creator")) : null;
        this.idea = inits.isInitialized("idea") ? new kr.co.conceptbe.idea.domain.QIdea(forProperty("idea"), inits.get("idea")) : null;
        this.parentComment = inits.isInitialized("parentComment") ? new QComment(forProperty("parentComment"), inits.get("parentComment")) : null;
    }

}

