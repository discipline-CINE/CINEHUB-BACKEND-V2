package Discipline.CineHub.dto.actor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailCommentDto {
  Long id;
  String comment;
  String username;

  public DetailCommentDto(long id, String comment, String username) {
    this.id = id;
    this.comment = comment;
    this.username = username;
  }
}
