package Discipline.CineHub.entity.external;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecommendationEntity is a Querydsl query type for RecommendationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecommendationEntity extends EntityPathBase<RecommendationEntity> {

    private static final long serialVersionUID = -1090945688L;

    public static final QRecommendationEntity recommendationEntity = new QRecommendationEntity("recommendationEntity");

    public final NumberPath<Double> distance = createNumber("distance", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imagePath = createString("imagePath");

    public final StringPath inputImage = createString("inputImage");

    public final StringPath url = createString("url");

    public QRecommendationEntity(String variable) {
        super(RecommendationEntity.class, forVariable(variable));
    }

    public QRecommendationEntity(Path<? extends RecommendationEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecommendationEntity(PathMetadata metadata) {
        super(RecommendationEntity.class, metadata);
    }

}

