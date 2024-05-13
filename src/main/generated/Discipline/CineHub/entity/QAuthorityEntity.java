package Discipline.CineHub.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuthorityEntity is a Querydsl query type for AuthorityEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthorityEntity extends EntityPathBase<AuthorityEntity> {

    private static final long serialVersionUID = 1676293807L;

    public static final QAuthorityEntity authorityEntity = new QAuthorityEntity("authorityEntity");

    public final StringPath authorityName = createString("authorityName");

    public QAuthorityEntity(String variable) {
        super(AuthorityEntity.class, forVariable(variable));
    }

    public QAuthorityEntity(Path<? extends AuthorityEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthorityEntity(PathMetadata metadata) {
        super(AuthorityEntity.class, metadata);
    }

}

