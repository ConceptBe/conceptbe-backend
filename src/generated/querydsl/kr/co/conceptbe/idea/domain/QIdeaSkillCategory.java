package kr.co.conceptbe.idea.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIdeaSkillCategory is a Querydsl query type for IdeaSkillCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIdeaSkillCategory extends EntityPathBase<IdeaSkillCategory> {

    private static final long serialVersionUID = 553065063L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIdeaSkillCategory ideaSkillCategory = new QIdeaSkillCategory("ideaSkillCategory");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QIdea idea;

    public final kr.co.conceptbe.skill.domain.QSkillCategory skillCategory;

    public QIdeaSkillCategory(String variable) {
        this(IdeaSkillCategory.class, forVariable(variable), INITS);
    }

    public QIdeaSkillCategory(Path<? extends IdeaSkillCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIdeaSkillCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIdeaSkillCategory(PathMetadata metadata, PathInits inits) {
        this(IdeaSkillCategory.class, metadata, inits);
    }

    public QIdeaSkillCategory(Class<? extends IdeaSkillCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.idea = inits.isInitialized("idea") ? new QIdea(forProperty("idea"), inits.get("idea")) : null;
        this.skillCategory = inits.isInitialized("skillCategory") ? new kr.co.conceptbe.skill.domain.QSkillCategory(forProperty("skillCategory"), inits.get("skillCategory")) : null;
    }

}

