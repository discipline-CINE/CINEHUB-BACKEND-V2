package Discipline.CineHub.dto.expert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDto {
  String name;
  String phone;
  String email;
  LocalDate reservationDate;

  public ReservationDto(String name, String phone, String email, LocalDate reservationDate) {
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.reservationDate = reservationDate;
  }
}
