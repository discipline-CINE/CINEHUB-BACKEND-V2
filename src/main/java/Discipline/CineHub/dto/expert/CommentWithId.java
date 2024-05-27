package Discipline.CineHub.dto.expert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentWithId {
  long id;
  String comment;
  String username;

  public CommentWithId(long id, String comment, String username) {
    this.id = id;
    this.comment = comment;
    this.username = username;
  }
}
