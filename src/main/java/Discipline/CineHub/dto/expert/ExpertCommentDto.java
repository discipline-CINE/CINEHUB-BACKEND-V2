package Discipline.CineHub.dto.expert;

import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.expert.ExpertBoard;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExpertCommentDto {
  String comment;
  ExpertBoard expertBoard;
  UserEntity user;

  public ExpertCommentDto(String comment, ExpertBoard expertBoard, UserEntity user) {
    this.comment = comment;
    this.expertBoard = expertBoard;
    this.user = user;
  }
}
