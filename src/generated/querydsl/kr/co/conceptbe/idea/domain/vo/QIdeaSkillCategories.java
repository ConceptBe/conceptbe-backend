package kr.co.conceptbe.idea.domain.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIdeaSkillCategories is a Querydsl query type for IdeaSkillCategories
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QIdeaSkillCategories extends BeanPath<IdeaSkillCategories> {

    private static final long serialVersionUID = -247447534L;

    public static final QIdeaSkillCategories ideaSkillCategories1 = new QIdeaSkillCategories("ideaSkillCategories1");

    public final ListPath<kr.co.conceptbe.idea.domain.IdeaSkillCategory, kr.co.conceptbe.idea.domain.QIdeaSkillCategory> ideaSkillCategories = this.<kr.co.conceptbe.idea.domain.IdeaSkillCategory, kr.co.conceptbe.idea.domain.QIdeaSkillCategory>createList("ideaSkillCategories", kr.co.conceptbe.idea.domain.IdeaSkillCategory.class, kr.co.conceptbe.idea.domain.QIdeaSkillCategory.class, PathInits.DIRECT2);

    public QIdeaSkillCategories(String variable) {
        super(IdeaSkillCategories.class, forVariable(variable));
    }

    public QIdeaSkillCategories(Path<? extends IdeaSkillCategories> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIdeaSkillCategories(PathMetadata metadata) {
        super(IdeaSkillCategories.class, metadata);
    }

}

