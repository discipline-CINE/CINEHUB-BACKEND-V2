package Discipline.CineHub.dto.actor;

import Discipline.CineHub.entity.actor.Thumbnail;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ThumbnailDto {
  private Long id;
  private String originFileName;
  private String fullPath;

  public Thumbnail toEntity(){
    return Thumbnail.builder()
            .id(this.id)
            .originFileName(this.originFileName)
            .fullPath(this.fullPath)
            .build();
  }

  @Builder
  public ThumbnailDto(Long id, String originFileName, String fullPath){
    this.id = id;
    this.originFileName = originFileName;
    this.fullPath = fullPath;
  }
}
