package kr.co.conceptbe.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberSkillCategory is a Querydsl query type for MemberSkillCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberSkillCategory extends EntityPathBase<MemberSkillCategory> {

    private static final long serialVersionUID = -1569180191L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberSkillCategory memberSkillCategory = new QMemberSkillCategory("memberSkillCategory");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final kr.co.conceptbe.skill.domain.QSkillCategory skillCategory;

    public final EnumPath<kr.co.conceptbe.skill.domain.SkillLevel> skillLevel = createEnum("skillLevel", kr.co.conceptbe.skill.domain.SkillLevel.class);

    public QMemberSkillCategory(String variable) {
        this(MemberSkillCategory.class, forVariable(variable), INITS);
    }

    public QMemberSkillCategory(Path<? extends MemberSkillCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberSkillCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberSkillCategory(PathMetadata metadata, PathInits inits) {
        this(MemberSkillCategory.class, metadata, inits);
    }

    public QMemberSkillCategory(Class<? extends MemberSkillCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.skillCategory = inits.isInitialized("skillCategory") ? new kr.co.conceptbe.skill.domain.QSkillCategory(forProperty("skillCategory"), inits.get("skillCategory")) : null;
    }

}

