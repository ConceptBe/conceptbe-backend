package kr.co.conceptbe.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberPurpose is a Querydsl query type for MemberPurpose
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberPurpose extends EntityPathBase<MemberPurpose> {

    private static final long serialVersionUID = -762501616L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberPurpose memberPurpose = new QMemberPurpose("memberPurpose");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final kr.co.conceptbe.purpose.domain.QPurpose purpose;

    public QMemberPurpose(String variable) {
        this(MemberPurpose.class, forVariable(variable), INITS);
    }

    public QMemberPurpose(Path<? extends MemberPurpose> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberPurpose(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberPurpose(PathMetadata metadata, PathInits inits) {
        this(MemberPurpose.class, metadata, inits);
    }

    public QMemberPurpose(Class<? extends MemberPurpose> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.purpose = inits.isInitialized("purpose") ? new kr.co.conceptbe.purpose.domain.QPurpose(forProperty("purpose")) : null;
    }

}

