package Discipline.CineHub.dto.expert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PriceFeatDto {
  String label;
  String v1;
  String v2;
  String v3;

  public PriceFeatDto(String label, String v1, String v2, String v3) {
    this.label = label;
    this.v1 = v1;
    this.v2 = v2;
    this.v3 = v3;
  }
}
