package Discipline.CineHub.entity.expert;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExpertComment is a Querydsl query type for ExpertComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExpertComment extends EntityPathBase<ExpertComment> {

    private static final long serialVersionUID = -843232312L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExpertComment expertComment = new QExpertComment("expertComment");

    public final StringPath comment = createString("comment");

    public final QExpertBoard expertBoard;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final Discipline.CineHub.entity.QUserEntity user;

    public QExpertComment(String variable) {
        this(ExpertComment.class, forVariable(variable), INITS);
    }

    public QExpertComment(Path<? extends ExpertComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExpertComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExpertComment(PathMetadata metadata, PathInits inits) {
        this(ExpertComment.class, metadata, inits);
    }

    public QExpertComment(Class<? extends ExpertComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.expertBoard = inits.isInitialized("expertBoard") ? new QExpertBoard(forProperty("expertBoard"), inits.get("expertBoard")) : null;
        this.user = inits.isInitialized("user") ? new Discipline.CineHub.entity.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

