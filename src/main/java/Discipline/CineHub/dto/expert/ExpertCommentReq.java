package Discipline.CineHub.dto.expert;

import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.expert.ExpertBoard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExpertCommentReq {
  String comment;
  Long expertBoardId;

  public ExpertCommentReq(String comment, Long expertBoardId, UserEntity user) {
    this.comment = comment;
    this.expertBoardId = expertBoardId;
  }
}
