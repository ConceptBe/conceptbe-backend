package kr.co.conceptbe.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOauthId is a Querydsl query type for OauthId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QOauthId extends BeanPath<OauthId> {

    private static final long serialVersionUID = -2052502530L;

    public static final QOauthId oauthId = new QOauthId("oauthId");

    public final StringPath oauthServerId = createString("oauthServerId");

    public final EnumPath<OauthServerType> oauthServerType = createEnum("oauthServerType", OauthServerType.class);

    public QOauthId(String variable) {
        super(OauthId.class, forVariable(variable));
    }

    public QOauthId(Path<? extends OauthId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOauthId(PathMetadata metadata) {
        super(OauthId.class, metadata);
    }

}

