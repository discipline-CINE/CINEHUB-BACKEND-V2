package Discipline.CineHub.entity.actor;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActor is a Querydsl query type for Actor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActor extends EntityPathBase<Actor> {

    private static final long serialVersionUID = 1302329061L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActor actor = new QActor("actor");

    public final SetPath<ActorComment, QActorComment> actorComments = this.<ActorComment, QActorComment>createSet("actorComments", ActorComment.class, QActorComment.class, PathInits.DIRECT2);

    public final NumberPath<Integer> birth = createNumber("birth", Integer.class);

    public final StringPath content = createString("content");

    public final EnumPath<GenderType> gender = createEnum("gender", GenderType.class);

    public final NumberPath<Double> height = createNumber("height", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath sns = createString("sns");

    public final SimplePath<java.net.URL> ThumbnailId = createSimple("ThumbnailId", java.net.URL.class);

    public final Discipline.CineHub.entity.QUserEntity user;

    public final NumberPath<Double> weight = createNumber("weight", Double.class);

    public QActor(String variable) {
        this(Actor.class, forVariable(variable), INITS);
    }

    public QActor(Path<? extends Actor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QActor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QActor(PathMetadata metadata, PathInits inits) {
        this(Actor.class, metadata, inits);
    }

    public QActor(Class<? extends Actor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new Discipline.CineHub.entity.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

