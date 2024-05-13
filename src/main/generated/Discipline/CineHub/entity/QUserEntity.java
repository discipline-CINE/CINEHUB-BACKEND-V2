package Discipline.CineHub.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -2042121147L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final Discipline.CineHub.entity.actor.QActor actor;

    public final StringPath address = createString("address");

    public final SetPath<AuthorityEntity, QAuthorityEntity> authorities = this.<AuthorityEntity, QAuthorityEntity>createSet("authorities", AuthorityEntity.class, QAuthorityEntity.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final Discipline.CineHub.entity.expert.QExpertBoard expertBoard;

    public final ListPath<Discipline.CineHub.entity.expert.ExpertComment, Discipline.CineHub.entity.expert.QExpertComment> expertComment = this.<Discipline.CineHub.entity.expert.ExpertComment, Discipline.CineHub.entity.expert.QExpertComment>createList("expertComment", Discipline.CineHub.entity.expert.ExpertComment.class, Discipline.CineHub.entity.expert.QExpertComment.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final StringPath phonenumber = createString("phonenumber");

    public final StringPath provider = createString("provider");

    public final StringPath role = createString("role");

    public final StringPath username = createString("username");

    public QUserEntity(String variable) {
        this(UserEntity.class, forVariable(variable), INITS);
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserEntity(PathMetadata metadata, PathInits inits) {
        this(UserEntity.class, metadata, inits);
    }

    public QUserEntity(Class<? extends UserEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.actor = inits.isInitialized("actor") ? new Discipline.CineHub.entity.actor.QActor(forProperty("actor"), inits.get("actor")) : null;
        this.expertBoard = inits.isInitialized("expertBoard") ? new Discipline.CineHub.entity.expert.QExpertBoard(forProperty("expertBoard"), inits.get("expertBoard")) : null;
    }

}

