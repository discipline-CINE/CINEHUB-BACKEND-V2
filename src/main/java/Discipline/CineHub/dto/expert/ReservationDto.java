package Discipline.CineHub.dto.expert;

import Discipline.CineHub.entity.expert.ConfirmType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDto {
  Long id;
  String name;
  String phone;
  String email;
  LocalDate reservationDate;
  ConfirmType confirmType;

  public ReservationDto(Long id, String name, String phone, String email, LocalDate reservationDate, ConfirmType confirmType) {
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.reservationDate = reservationDate;
    this.confirmType = confirmType;
  }
}
