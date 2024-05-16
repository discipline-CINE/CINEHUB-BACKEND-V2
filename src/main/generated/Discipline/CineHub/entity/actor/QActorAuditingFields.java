package Discipline.CineHub.entity.actor;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QActorAuditingFields is a Querydsl query type for ActorAuditingFields
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QActorAuditingFields extends EntityPathBase<ActorAuditingFields> {

    private static final long serialVersionUID = 491383301L;

    public static final QActorAuditingFields actorAuditingFields = new QActorAuditingFields("actorAuditingFields");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public QActorAuditingFields(String variable) {
        super(ActorAuditingFields.class, forVariable(variable));
    }

    public QActorAuditingFields(Path<? extends ActorAuditingFields> path) {
        super(path.getType(), path.getMetadata());
    }

    public QActorAuditingFields(PathMetadata metadata) {
        super(ActorAuditingFields.class, metadata);
    }

}

