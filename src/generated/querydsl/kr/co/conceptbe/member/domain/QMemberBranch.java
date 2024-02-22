package kr.co.conceptbe.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberBranch is a Querydsl query type for MemberBranch
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberBranch extends EntityPathBase<MemberBranch> {

    private static final long serialVersionUID = -1952704912L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberBranch memberBranch = new QMemberBranch("memberBranch");

    public final kr.co.conceptbe.branch.domain.QBranch branch;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public QMemberBranch(String variable) {
        this(MemberBranch.class, forVariable(variable), INITS);
    }

    public QMemberBranch(Path<? extends MemberBranch> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberBranch(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberBranch(PathMetadata metadata, PathInits inits) {
        this(MemberBranch.class, metadata, inits);
    }

    public QMemberBranch(Class<? extends MemberBranch> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.branch = inits.isInitialized("branch") ? new kr.co.conceptbe.branch.domain.QBranch(forProperty("branch")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

