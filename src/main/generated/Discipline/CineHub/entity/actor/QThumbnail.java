package Discipline.CineHub.entity.actor;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QThumbnail is a Querydsl query type for Thumbnail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QThumbnail extends EntityPathBase<Thumbnail> {

    private static final long serialVersionUID = 915973596L;

    public static final QThumbnail thumbnail = new QThumbnail("thumbnail");

    public final StringPath fullPath = createString("fullPath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath originFileName = createString("originFileName");

    public QThumbnail(String variable) {
        super(Thumbnail.class, forVariable(variable));
    }

    public QThumbnail(Path<? extends Thumbnail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QThumbnail(PathMetadata metadata) {
        super(Thumbnail.class, metadata);
    }

}

