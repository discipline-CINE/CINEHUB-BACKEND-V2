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

    public static final QActor actor = new QActor("actor");

    public final QActorAuditingFields _super = new QActorAuditingFields(this);

    public final SetPath<ActorComment, QActorComment> actorComments = this.<ActorComment, QActorComment>createSet("actorComments", ActorComment.class, QActorComment.class, PathInits.DIRECT2);

    public final NumberPath<Integer> birth = createNumber("birth", Integer.class);

    public final StringPath career = createString("career");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final EnumPath<GenderType> gender = createEnum("gender", GenderType.class);

    public final NumberPath<Double> height = createNumber("height", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath specialty = createString("specialty");

    public final NumberPath<Long> ThumbnailId = createNumber("ThumbnailId", Long.class);

    public final NumberPath<Double> weight = createNumber("weight", Double.class);

    public QActor(String variable) {
        super(Actor.class, forVariable(variable));
    }

    public QActor(Path<? extends Actor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActor(PathMetadata metadata) {
        super(Actor.class, metadata);
    }

}

