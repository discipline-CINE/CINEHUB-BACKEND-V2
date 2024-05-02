package Discipline.CineHub.controller.expert;

import Discipline.CineHub.dto.expert.ReservationDto;
import Discipline.CineHub.service.expert.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expert")
public class ReservationController {
  @Autowired ReservationService reservationService;

  @PostMapping("/create-reservation")
  public void createReservation(Long expertBoardId, Long userId,ReservationDto reservationDto){
    reservationService.createReservation(expertBoardId, userId ,reservationDto);
  }

  @PostMapping("decide-reservation/{reservationId}")
  public ReservationDto decideReservation(@PathVariable("reservationId") Long id, String op){
    return reservationService.decideReservation(id, op);
  }
}
