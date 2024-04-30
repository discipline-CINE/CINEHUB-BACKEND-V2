package Discipline.CineHub.service.expert;

import Discipline.CineHub.dto.expert.ReservationDto;
import Discipline.CineHub.entity.expert.ConfirmType;
import Discipline.CineHub.entity.expert.ExpertBoard;
import Discipline.CineHub.entity.expert.Reservation;
import Discipline.CineHub.repository.expert.ExpertBoardRepository;
import Discipline.CineHub.repository.expert.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {
  @Autowired ReservationRepository reservationRepository;
  @Autowired ExpertBoardRepository expertBoardRepository;

  public void createReservation(Long expertBoardId, ReservationDto reservationDto){
    Optional<ExpertBoard> board = expertBoardRepository.findById(expertBoardId);

    Reservation reservation = new Reservation();
    reservation.setName(reservationDto.getName());
    reservation.setPhone(reservationDto.getPhone());
    reservation.setEmail(reservationDto.getEmail());
    reservation.setConfirm(ConfirmType.PROCEED);
    reservation.setReservationDate(reservationDto.getReservationDate());
    reservation.setExpertBoard(board.get());

    reservationRepository.save(reservation);
  }
}