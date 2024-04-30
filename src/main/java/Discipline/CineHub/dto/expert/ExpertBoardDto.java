package Discipline.CineHub.dto.expert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExpertBoardDto {
  String title;
  int price;
  String type;
  String content;

  public ExpertBoardDto(String title, int price, String type, String content) {
    this.title = title;
    this.price = price;
    this.type = type;
    this.content = content;
  }
}
