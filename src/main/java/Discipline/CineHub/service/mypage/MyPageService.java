package Discipline.CineHub.service.mypage;

import Discipline.CineHub.dto.expert.ReservationDto;
import Discipline.CineHub.dto.expert.ReservationDtoByUser;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.service.expert.ExpertBoardService;
import Discipline.CineHub.service.expert.ReservationService;
import Discipline.CineHub.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MyPageService {
  @Autowired
  ExpertBoardService expertBoardService;

  @Autowired
  UserService userService;

  @Autowired
  ReservationService reservationService;

  public List<ReservationDto> checkReservation(String username){
    Long id = userService.findByUsername(username).get().getExpertBoard().getId();
    return expertBoardService.checkReservation(id);
  }

  public List<ReservationDtoByUser> checkReservationByUser(String username){
    Optional<UserEntity> user = userService.findByUsername(username);
    return reservationService.findByUser(user.get());
  }
}
