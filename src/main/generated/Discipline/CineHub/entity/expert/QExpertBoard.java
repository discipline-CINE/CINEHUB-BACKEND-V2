package Discipline.CineHub.entity.expert;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExpertBoard is a Querydsl query type for ExpertBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExpertBoard extends EntityPathBase<ExpertBoard> {

    private static final long serialVersionUID = -1869966705L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExpertBoard expertBoard = new QExpertBoard("expertBoard");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> dPrice = createNumber("dPrice", Integer.class);

    public final NumberPath<Long> eId = createNumber("eId", Long.class);

    public final ListPath<ExpertComment, QExpertComment> expertComments = this.<ExpertComment, QExpertComment>createList("expertComments", ExpertComment.class, QExpertComment.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> pPrice = createNumber("pPrice", Integer.class);

    public final ListPath<PriceFeat, QPriceFeat> priceFeats = this.<PriceFeat, QPriceFeat>createList("priceFeats", PriceFeat.class, QPriceFeat.class, PathInits.DIRECT2);

    public final ListPath<Reservation, QReservation> reservations = this.<Reservation, QReservation>createList("reservations", Reservation.class, QReservation.class, PathInits.DIRECT2);

    public final NumberPath<Integer> sPrice = createNumber("sPrice", Integer.class);

    public final SimplePath<java.net.URL> thumbnail = createSimple("thumbnail", java.net.URL.class);

    public final StringPath title = createString("title");

    public final StringPath type = createString("type");

    public final Discipline.CineHub.entity.QUserEntity user;

    public QExpertBoard(String variable) {
        this(ExpertBoard.class, forVariable(variable), INITS);
    }

    public QExpertBoard(Path<? extends ExpertBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExpertBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExpertBoard(PathMetadata metadata, PathInits inits) {
        this(ExpertBoard.class, metadata, inits);
    }

    public QExpertBoard(Class<? extends ExpertBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new Discipline.CineHub.entity.QUserEntity(forProperty("user"), inits.get("user")) : null;
    }

}

