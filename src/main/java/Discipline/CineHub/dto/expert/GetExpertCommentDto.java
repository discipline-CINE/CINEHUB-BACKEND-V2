package Discipline.CineHub.dto.expert;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetExpertCommentDto {
  Long expertBoardId;
  String title;
  @Size(max = 50000) @Column(nullable = false, length = 50000) String comment;

  public GetExpertCommentDto(Long expertBoardId, String title, String comment) {
    this.expertBoardId = expertBoardId;
    this.title = title;
    this.comment = comment;
  }
}
