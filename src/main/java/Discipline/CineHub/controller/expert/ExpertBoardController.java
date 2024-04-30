package Discipline.CineHub.controller.expert;

import Discipline.CineHub.dto.expert.ExpertBoardDto;
import Discipline.CineHub.dto.expert.ReservationDto;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.service.expert.ExpertBoardService;
import Discipline.CineHub.service.user.UserService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/expert")
public class ExpertBoardController {

  @Autowired ExpertBoardService expertBoardService;

  @Autowired UserService userService;

  @PostMapping("/create-board")
  public void createBoard(ExpertBoardDto expertBoardDto, String username) {
    Optional<UserEntity> user = userService.findByUsername(username);
    if (user.get().getRole() == "USER") {
      System.out.println("Error");
    } else {
      expertBoardService.enrollExpertBoard(expertBoardDto, user.get());
    }
  }

  @GetMapping("/check-reservation")
  public List<ReservationDto> checkReservation(Long expertBoardId){
    return expertBoardService.checkReservation(expertBoardId);
  }
}
