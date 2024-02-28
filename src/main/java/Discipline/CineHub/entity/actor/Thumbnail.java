package Discipline.CineHub.entity.actor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Thumbnail {
  @Id
  @GeneratedValue
  private Long id;
  @Column(nullable = false)
  private String originFileName;  //확장자를 포함한 파일명
  @Column(nullable = false)
  private String fullPath;  //저장될 경로와 파일명 포함 --> 다운로드 할 때 이용

  @Builder
  public Thumbnail(Long id, String originFileName, String fullPath) {
    this.id = id;
    this.originFileName = originFileName;
    this.fullPath = fullPath;
  }
}
