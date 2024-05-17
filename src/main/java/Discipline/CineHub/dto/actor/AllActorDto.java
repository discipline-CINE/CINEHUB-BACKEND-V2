package Discipline.CineHub.dto.actor;


import Discipline.CineHub.dto.external.RecommendationResponse;
import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.entity.actor.ActorComment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AllActorDto {
  Long id;
  String content;
  String sns;
  String name;
  String gender;
  Integer birth;
  Double height;
  Double weight;
  URL thumbnailId;
  String username;
  List<String> actorComments;
  List<RecommendationResponse> recommendationUrls;

  public AllActorDto(Long id, String content, String sns, String name, String gender, Integer birth, Double height, Double weight, URL thumbnailId, String username, List<RecommendationResponse> recommendationUrls, List<String> actorComments) {
    this.id = id;
    this.content = content;
    this.sns = sns;
    this.name = name;
    this.gender = gender;
    this.birth = birth;
    this.height = height;
    this.weight = weight;
    this.thumbnailId = thumbnailId;
    this.username = username;
    this.recommendationUrls = recommendationUrls;
    this.actorComments = actorComments;
  }
}
