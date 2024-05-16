package Discipline.CineHub.dto.actor;


import Discipline.CineHub.entity.actor.Actor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AllActorDto {
  Long id;
  String name;
  String gender;
  Integer birth;
  Double height;
  Double weight;
  URL thumbnailId;
  String username;
  List<String> recommendationUrls;

  public AllActorDto(Long id, String name, String gender, Integer birth, Double height, Double weight, URL thumbnailId, String username, List<String> recommendationUrls) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.birth = birth;
    this.height = height;
    this.weight = weight;
    this.thumbnailId = thumbnailId;
    this.username = username;
    this.recommendationUrls = recommendationUrls;
  }
}
