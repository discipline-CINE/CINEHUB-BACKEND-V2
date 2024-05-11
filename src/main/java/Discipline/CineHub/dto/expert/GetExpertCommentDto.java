package Discipline.CineHub.dto.expert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetExpertCommentDto {
  Long expertBoardId;
  String title;
  String comment;

  public GetExpertCommentDto(Long expertBoardId, String title, String comment) {
    this.expertBoardId = expertBoardId;
    this.title = title;
    this.comment = comment;
  }
}
