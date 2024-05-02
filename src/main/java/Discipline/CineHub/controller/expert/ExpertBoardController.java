package Discipline.CineHub.controller.expert;

import Discipline.CineHub.dto.expert.EachBoardDto;
import Discipline.CineHub.dto.expert.ExpertBoardDto;
import Discipline.CineHub.dto.expert.GetAllBoardDto;
import Discipline.CineHub.dto.expert.ReservationDto;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.expert.ExpertBoard;
import Discipline.CineHub.service.expert.ExpertBoardService;
import Discipline.CineHub.service.user.UserService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/expert")
public class ExpertBoardController {
  @Autowired
  ExpertBoardService expertBoardService;

  @Autowired
  UserService userService;

  @PostMapping("/create-board")
  public void createBoard(ExpertBoardDto expertBoardDto, String username) {
    Optional<UserEntity> user = userService.findByUsername(username);
    if (user.get().getRole() == "USER") {
      System.out.println("Error");
    } else {
      expertBoardService.enrollExpertBoard(expertBoardDto, user.get());
    }
  }

  @PostMapping("/check-reservation")
  public List<ReservationDto> checkReservation(Long expertBoardId){
    return expertBoardService.checkReservation(expertBoardId);
  }

  @GetMapping("/all-board")
  public List<GetAllBoardDto> findAllExpertBoards(){
    return expertBoardService.findAllExpertBoards();
  }

  @GetMapping("/board/{id}")
  public EachBoardDto getBoard(@PathVariable("id") Long id){
    return expertBoardService.findById(id);
  }
}
