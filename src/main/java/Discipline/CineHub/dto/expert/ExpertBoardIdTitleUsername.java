package Discipline.CineHub.dto.expert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExpertBoardIdTitleUsername {
  Long id;
  String title;
  String username;

  public ExpertBoardIdTitleUsername(Long id, String title, String username) {
    this.id = id;
    this.title = title;
    this.username = username;
  }
}
