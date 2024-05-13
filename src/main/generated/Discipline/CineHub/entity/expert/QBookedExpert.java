package Discipline.CineHub.entity.expert;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookedExpert is a Querydsl query type for BookedExpert
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookedExpert extends EntityPathBase<BookedExpert> {

    private static final long serialVersionUID = 600486975L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookedExpert bookedExpert = new QBookedExpert("bookedExpert");

    public final StringPath bookingConfirmationCode = createString("bookingConfirmationCode");

    public final NumberPath<Long> bookingId = createNumber("bookingId", Long.class);

    public final DatePath<java.time.LocalDate> checkInDate = createDate("checkInDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> checkOutDate = createDate("checkOutDate", java.time.LocalDate.class);

    public final StringPath email = createString("email");

    public final QExpert expert;

    public final StringPath fullName = createString("fullName");

    public QBookedExpert(String variable) {
        this(BookedExpert.class, forVariable(variable), INITS);
    }

    public QBookedExpert(Path<? extends BookedExpert> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookedExpert(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookedExpert(PathMetadata metadata, PathInits inits) {
        this(BookedExpert.class, metadata, inits);
    }

    public QBookedExpert(Class<? extends BookedExpert> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.expert = inits.isInitialized("expert") ? new QExpert(forProperty("expert")) : null;
    }

}

