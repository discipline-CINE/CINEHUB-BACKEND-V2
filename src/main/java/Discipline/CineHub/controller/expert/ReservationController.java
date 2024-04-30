package Discipline.CineHub.controller.expert;

import Discipline.CineHub.dto.expert.ReservationDto;
import Discipline.CineHub.service.expert.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {
  @Autowired ReservationService reservationService;

  @PostMapping("/create-reservation")
  public void createReservation(Long expertBoardId, ReservationDto reservationDto){
    reservationService.createReservation(expertBoardId, reservationDto);
  }
}
