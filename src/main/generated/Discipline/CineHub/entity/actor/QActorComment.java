package Discipline.CineHub.entity.actor;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActorComment is a Querydsl query type for ActorComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActorComment extends EntityPathBase<ActorComment> {

    private static final long serialVersionUID = -622136390L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActorComment actorComment = new QActorComment("actorComment");

    public final QActor actor;

    public final NumberPath<Long> aId = createNumber("aId", Long.class);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath username = createString("username");

    public QActorComment(String variable) {
        this(ActorComment.class, forVariable(variable), INITS);
    }

    public QActorComment(Path<? extends ActorComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QActorComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QActorComment(PathMetadata metadata, PathInits inits) {
        this(ActorComment.class, metadata, inits);
    }

    public QActorComment(Class<? extends ActorComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.actor = inits.isInitialized("actor") ? new QActor(forProperty("actor"), inits.get("actor")) : null;
    }

}

