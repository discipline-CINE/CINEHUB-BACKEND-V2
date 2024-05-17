package Discipline.CineHub.entity.expert;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QExpertImg is a Querydsl query type for ExpertImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExpertImg extends EntityPathBase<ExpertImg> {

    private static final long serialVersionUID = -1289088596L;

    public static final QExpertImg expertImg = new QExpertImg("expertImg");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SimplePath<java.net.URL> img = createSimple("img", java.net.URL.class);

    public QExpertImg(String variable) {
        super(ExpertImg.class, forVariable(variable));
    }

    public QExpertImg(Path<? extends ExpertImg> path) {
        super(path.getType(), path.getMetadata());
    }

    public QExpertImg(PathMetadata metadata) {
        super(ExpertImg.class, metadata);
    }

}

