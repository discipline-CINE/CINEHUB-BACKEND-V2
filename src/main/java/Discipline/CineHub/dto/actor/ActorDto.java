package Discipline.CineHub.dto.actor;

import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.entity.actor.GenderType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@NoArgsConstructor
@Data
public class ActorDto {
  private String name;
  private GenderType gender;
  private Integer birth;
  private Double height;
  private Double weight;
  private String content;
  private String sns;
  private URL ThumbnailId;

  public Actor toEntity(){
    return Actor.builder()
            .name(this.name)
            .gender(this.gender)
            .birth(this.birth)
            .height(this.height)
            .weight(this.weight)
            .content(this.content)
            .sns(this.sns)
            .thumbnailId(this.ThumbnailId)
            .build();
  }

  @Builder
  public ActorDto(String name, GenderType gender, Integer birth, Double height, Double weight, String content, String sns, URL thumbnailId) {
    this.name = name;
    this.gender = gender;
    this.birth = birth;
    this.height = height;
    this.weight = weight;
    this.content = content;
    this.sns = sns;
    ThumbnailId = thumbnailId;
  }
}
