package Discipline.CineHub.controller.external;

import Discipline.CineHub.dto.external.RecommendationDto;
import Discipline.CineHub.entity.external.RecommendationEntity;
import Discipline.CineHub.service.external.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/recommendation")
    public ResponseEntity<List<RecommendationEntity>> saveRecommendation(@RequestBody List<RecommendationDto> dtoList) {
        List<RecommendationEntity> savedRecommendations = recommendationService.saveRecommendations(dtoList);
        return ResponseEntity.ok(savedRecommendations);
    }
}