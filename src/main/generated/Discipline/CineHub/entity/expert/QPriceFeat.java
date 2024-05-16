package Discipline.CineHub.entity.expert;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPriceFeat is a Querydsl query type for PriceFeat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPriceFeat extends EntityPathBase<PriceFeat> {

    private static final long serialVersionUID = 743622286L;

    public static final QPriceFeat priceFeat = new QPriceFeat("priceFeat");

    public final StringPath dFeat = createString("dFeat");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath label = createString("label");

    public final StringPath pFeat = createString("pFeat");

    public final StringPath sFeat = createString("sFeat");

    public QPriceFeat(String variable) {
        super(PriceFeat.class, forVariable(variable));
    }

    public QPriceFeat(Path<? extends PriceFeat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPriceFeat(PathMetadata metadata) {
        super(PriceFeat.class, metadata);
    }

}

