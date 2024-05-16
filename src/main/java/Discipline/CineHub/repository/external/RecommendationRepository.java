package Discipline.CineHub.repository.external;

import Discipline.CineHub.entity.external.RecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<RecommendationEntity, Long> {

    List<RecommendationEntity> findByUrl(String url);

    List<RecommendationEntity> findByInputImage(String inputImage);
}
