package Discipline.CineHub.dto.actor;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

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

  public AllActorDto(Long id, String name, String gender, Integer birth, Double height, Double weight, URL thumbnailId, String username) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.birth = birth;
    this.height = height;
    this.weight = weight;
    this.thumbnailId = thumbnailId;
    this.username = username;
  }
}
