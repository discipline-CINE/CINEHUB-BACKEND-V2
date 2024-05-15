package Discipline.CineHub.service.external;

import Discipline.CineHub.dto.external.RecommendationDto;
import Discipline.CineHub.entity.external.RecommendationEntity;
import Discipline.CineHub.repository.external.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    public static String getFileNameFromURL(URL url) {
        String filePath = url.getPath();
        return filePath.substring(filePath.lastIndexOf('/') + 1);
    }

    public List<String> getUrlsByActorThumbnail(URL thumbnailUrl) {
        if (thumbnailUrl == null) {
            return List.of();
        }

        String thumbnailFileName = getFileNameFromURL(thumbnailUrl);
        List<RecommendationEntity> recommendations = recommendationRepository.findByInputImage(thumbnailFileName);

        return recommendations.stream()
                .map(RecommendationEntity::getUrl)
                .collect(Collectors.toList());
    }

    public List<RecommendationEntity> saveRecommendations(List<RecommendationDto> dtoList) {
        List<RecommendationEntity> recommendations = dtoList.stream().map(dto -> {
            RecommendationEntity recommendation = new RecommendationEntity();
            recommendation.setImagePath(dto.getImagePath());
            recommendation.setDistance(dto.getDistance());
            recommendation.setUrl(dto.getUrl());
            recommendation.setInputImage(dto.getInputImage());  // 새로운 필드 설정
            return recommendation;
        }).collect(Collectors.toList());
        return recommendationRepository.saveAll(recommendations);
    }
}
