package Discipline.CineHub.entity.expert;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExpert is a Querydsl query type for Expert
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExpert extends EntityPathBase<Expert> {

    private static final long serialVersionUID = 707687063L;

    public static final QExpert expert = new QExpert("expert");

    public final ListPath<BookedExpert, QBookedExpert> bookings = this.<BookedExpert, QBookedExpert>createList("bookings", BookedExpert.class, QBookedExpert.class, PathInits.DIRECT2);

    public final StringPath expertType = createString("expertType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isBooked = createBoolean("isBooked");

    public final StringPath summary = createString("summary");

    public final SimplePath<java.sql.Blob> thumbnail = createSimple("thumbnail", java.sql.Blob.class);

    public QExpert(String variable) {
        super(Expert.class, forVariable(variable));
    }

    public QExpert(Path<? extends Expert> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExpert(PathMetadata metadata) {
        super(Expert.class, metadata);
    }

}

