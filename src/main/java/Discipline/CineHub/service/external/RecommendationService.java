package Discipline.CineHub.service.external;

import Discipline.CineHub.dto.actor.AllActorDto;
import Discipline.CineHub.dto.external.RecommendationDto;
import Discipline.CineHub.dto.external.RecommendationResponse;
import Discipline.CineHub.entity.external.RecommendationEntity;
import Discipline.CineHub.repository.actor.ActorRepository;
import Discipline.CineHub.repository.external.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final String s3_base_url = "https://discipline-actor.s3.ap-northeast-2.amazonaws.com";
    private final RecommendationRepository recommendationRepository;



    @Autowired
    public RecommendationService(RecommendationRepository recommendationRepository, ActorRepository actorRepository) {
        this.recommendationRepository = recommendationRepository;
    }



    public List<RecommendationEntity> saveRecommendations(List<RecommendationDto> dtoList) {
        List<RecommendationEntity> recommendations = dtoList.stream().map(dto -> {
            RecommendationEntity recommendation = new RecommendationEntity();
            recommendation.setImagePath(dto.getImagePath());
            recommendation.setDistance(dto.getDistance());
            recommendation.setUrl(dto.getUrl());
            recommendation.setInputImage(dto.getInputImage());
            return recommendation;
        }).collect(Collectors.toList());
        return recommendationRepository.saveAll(recommendations);
    }
}