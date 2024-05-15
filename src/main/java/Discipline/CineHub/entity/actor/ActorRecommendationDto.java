package Discipline.CineHub.entity.actor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorRecommendationDto {
    private Actor actor;
    private List<String> recommendationUrls;
}