package Discipline.CineHub.dto.expert;

import Discipline.CineHub.entity.expert.PriceFeat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetAllBoardDto {
  Long id;
  String title;
  int sPrice;
  int dPrice;
  int pPrice;
  String type;
  @Size(max = 50000) @Column(nullable = false, length = 50000) String content;
  URL thumbnail;
  List<PriceFeatDto> priceFeats;

  public GetAllBoardDto(Long id, String title, int sPrice,int dPrice,int pPrice, String type, String content, URL thumbnail, List<PriceFeatDto> priceFeats) {
    this.id = id;
    this.title = title;
    this.sPrice = sPrice;
    this.dPrice = dPrice;
    this.pPrice = pPrice;
    this.type = type;
    this.content = content;
    this.thumbnail = thumbnail;
    this.priceFeats = priceFeats;
  }
}
