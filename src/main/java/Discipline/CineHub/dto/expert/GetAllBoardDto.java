package Discipline.CineHub.dto.expert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetAllBoardDto {
  Long id;
  String title;
  int price;
  String type;
  String content;

  public GetAllBoardDto(Long id, String title, int price, String type, String content) {
    this.id = id;
    this.title = title;
    this.price = price;
    this.type = type;
    this.content = content;
  }
}
