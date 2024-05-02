package Discipline.CineHub.dto.expert;

import Discipline.CineHub.entity.expert.ConfirmType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDtoByUser {
  Long id;
  String name;
  String phone;
  String email;
  LocalDate reservationDate;
  ConfirmType confirmType;
  Long boardId;
  String boardTitle;
  int boardPrice;

  public ReservationDtoByUser(Long id, String name, String phone, String email, LocalDate reservationDate, ConfirmType confirmType, Long boardId, String boardTitle, int boardPrice) {
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.email = email;
    this.reservationDate = reservationDate;
    this.confirmType = confirmType;
    this.boardId = boardId;
    this.boardTitle = boardTitle;
    this.boardPrice = boardPrice;
  }
}
