package kr.co.conceptbe.skill.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSkillCategory is a Querydsl query type for SkillCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSkillCategory extends EntityPathBase<SkillCategory> {

    private static final long serialVersionUID = 1101680640L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSkillCategory skillCategory = new QSkillCategory("skillCategory");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QSkillCategory parentSkillCategory;

    public QSkillCategory(String variable) {
        this(SkillCategory.class, forVariable(variable), INITS);
    }

    public QSkillCategory(Path<? extends SkillCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSkillCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSkillCategory(PathMetadata metadata, PathInits inits) {
        this(SkillCategory.class, metadata, inits);
    }

    public QSkillCategory(Class<? extends SkillCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parentSkillCategory = inits.isInitialized("parentSkillCategory") ? new QSkillCategory(forProperty("parentSkillCategory"), inits.get("parentSkillCategory")) : null;
    }

}

