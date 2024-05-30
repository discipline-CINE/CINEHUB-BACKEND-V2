package Discipline.CineHub.dto.expert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExpertBoardIdTitleUsername {
  Long id;
  Long resId;
  String title;
  String username;
  Boolean review;

  public ExpertBoardIdTitleUsername(Long id, Long resId,String title, String username) {
    this.id = id;
    this.resId = resId;
    this.title = title;
    this.username = username;
  }
}
