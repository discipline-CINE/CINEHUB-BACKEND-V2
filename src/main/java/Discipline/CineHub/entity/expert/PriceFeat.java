package Discipline.CineHub.entity.expert;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "price_faetuer")
public class PriceFeat {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  Long id;
  String label;
  // Standard 정보
  String sFeat;
  // Deluxe 정보
  String dFeat;
  // Premium 정보
  String pFeat;
}
