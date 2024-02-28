package Discipline.CineHub.dto.actor;

import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.entity.actor.GenderType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ActorDto {
  private String name;
  private GenderType gender;
  private Integer birth;
  private Double height;
  private Double weight;
  private String specialty;
  private String career;
  private String content;
  private Long ThumbnailId;

  public Actor toEntity(){
    return Actor.builder()
            .name(this.name)
            .gender(this.gender)
            .birth(this.birth)
            .height(this.height)
            .weight(this.weight)
            .specialty(this.specialty)
            .career(this.career)
            .content(this.content)
            .thumbnailId(this.ThumbnailId)
            .build();
  }

  @Builder
  public ActorDto(String name, GenderType gender, Integer birth, Double height, Double weight, String specialty, String career, String content, Long thumbnailId) {
    this.name = name;
    this.gender = gender;
    this.birth = birth;
    this.height = height;
    this.weight = weight;
    this.specialty = specialty;
    this.career = career;
    this.content = content;
    ThumbnailId = thumbnailId;
  }
}
