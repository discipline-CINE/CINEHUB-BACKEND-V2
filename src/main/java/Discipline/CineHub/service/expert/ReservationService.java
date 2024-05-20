package Discipline.CineHub.service.expert;

import Discipline.CineHub.dto.expert.ExpertBoardIdTitleUsername;
import Discipline.CineHub.dto.expert.ReservationDto;
import Discipline.CineHub.dto.expert.ReservationDtoByUser;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.expert.ConfirmType;
import Discipline.CineHub.entity.expert.ExpertBoard;
import Discipline.CineHub.entity.expert.Reservation;
import Discipline.CineHub.repository.UserRepository;
import Discipline.CineHub.repository.expert.ExpertBoardRepository;
import Discipline.CineHub.repository.expert.ReservationRepository;
import Discipline.CineHub.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
  @Autowired ReservationRepository reservationRepository;
  @Autowired ExpertBoardRepository expertBoardRepository;
  @Autowired UserRepository userRepository;

  public void createReservation(String expertBoardId, ReservationDto reservationDto){
    Optional<ExpertBoard> board = expertBoardRepository.findById(Long.parseLong(expertBoardId));
    UserEntity user = userRepository.findByUsername(reservationDto.getName())
            .orElseThrow(() -> new RuntimeException("해당하는 유저가 없습니다."));

    Reservation reservation = new Reservation();
    reservation.setName(reservationDto.getName());
    reservation.setPhone(reservationDto.getPhone());
    reservation.setEmail(reservationDto.getEmail());
    reservation.setConfirm(ConfirmType.PROCEED);
    reservation.setReservationDate(reservationDto.getReservationDate());

    reservation.setExpertBoard(board.get());
    reservation.setUser(user);

    reservationRepository.save(reservation);
  }

  @Transactional
  public ReservationDto decideReservation(Long resId, String op){
    Reservation res = reservationRepository.findById(resId).get();
    switch (op){
      case "ACCEPT" : res.setConfirm(ConfirmType.ACCEPT); break;
      case "REJECT" : res.setConfirm(ConfirmType.REJECT); break;
      case "COMPLETE" : res.setConfirm(ConfirmType.COMPLETE); break;
      default: break;
    }
    return new ReservationDto(res.getId(), res.getName(), res.getPhone(),
            res.getEmail(), res.getReservationDate(), res.getConfirm());
  }

  public List<ReservationDtoByUser> findByUser(UserEntity user){
    List<Reservation> reservations = reservationRepository.findByUser(user);
    List<ReservationDtoByUser> res = new ArrayList<>();

    for(Reservation reservation : reservations){
      ReservationDtoByUser tmp =
              new ReservationDtoByUser(reservation.getId(), reservation.getName(), reservation.getPhone(),
                      reservation.getEmail(), reservation.getReservationDate(), reservation.getConfirm(),
                      reservation.getExpertBoard().getId(),
                      reservation.getExpertBoard().getTitle(),
                      reservation.getExpertBoard().getSPrice(),
                      reservation.getExpertBoard().getDPrice(),
                      reservation.getExpertBoard().getPPrice()
                      );
      res.add(tmp);
    }
    return res;
  }

  public List<ExpertBoardIdTitleUsername> checkComplete(UserEntity user){
    List<Reservation> reservations = reservationRepository.findByUser(user);
    List<ExpertBoardIdTitleUsername> res = new ArrayList<>();

    for (Reservation reservation : reservations){
      if(reservation.getConfirm().equals(ConfirmType.COMPLETE)){
        ExpertBoardIdTitleUsername tmp = new ExpertBoardIdTitleUsername(
                reservation.getExpertBoard().getId(),
                reservation.getExpertBoard().getTitle(),
                reservation.getUser().getUsername()
        );
        res.add(tmp);
      }
    }
    return res;
  }

}