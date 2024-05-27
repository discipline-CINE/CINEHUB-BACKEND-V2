package Discipline.CineHub.controller.expert;

import Discipline.CineHub.dto.expert.*;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.expert.ExpertBoard;
import Discipline.CineHub.service.actor.StorageService;
import Discipline.CineHub.service.expert.ExpertBoardService;
import Discipline.CineHub.service.expert.ExpertBoardStorageService;
import Discipline.CineHub.service.mypage.MyPageService;
import Discipline.CineHub.service.user.UserService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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

  @Autowired
  ExpertBoardStorageService expertBoardStorageService;

  @Autowired
  MyPageService myPageService;

  @PostMapping("/create-board")
  public void createBoard(ExpertBoardRequest expertBoardRequest, String username) {
    UserEntity user = userService.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("해당하는 유저가 없습니다."));

    if (user.getRole() == "USER") {
      new RuntimeException("해당 유저는 전문가가 아닙니다.");
    }
    else if (user.getExpertBoard() != null){
      throw new IllegalStateException("게시판이 이미 존재합니다.");
    }
    else {
      URL thumbnail = expertBoardStorageService.uploadFile(expertBoardRequest.getThumbnailImg());
      List<PriceFeatDto> priceFeatDtos = new ArrayList<>();
      for (List<String> priceFeat : expertBoardRequest.getPriceFeats()){
        PriceFeatDto priceFeatDto = new PriceFeatDto(
                priceFeat.get(0),
                priceFeat.get(1),
                priceFeat.get(2),
                priceFeat.get(3)
        );
        priceFeatDtos.add(priceFeatDto);
      }

      ExpertBoardDto expertBoardDto = new ExpertBoardDto(
              expertBoardRequest.getTitle(),
              expertBoardRequest.getSPrice(),
              expertBoardRequest.getDPrice(),
              expertBoardRequest.getPPrice(),
              expertBoardRequest.getType(),
              expertBoardRequest.getContent(),
              thumbnail,
              priceFeatDtos
      );

      expertBoardService.enrollExpertBoard(expertBoardDto, user);
    }
  }

  @PutMapping("/boards/{id}")
  public void updateBoard(@PathVariable Long id, ExpertBoardRequest expertBoardRequest){
    URL thumbnail = expertBoardStorageService.uploadFile(expertBoardRequest.getThumbnailImg());
    List<PriceFeatDto> priceFeatDtos = new ArrayList<>();
    for (List<String> priceFeat : expertBoardRequest.getPriceFeats()){
      PriceFeatDto priceFeatDto = new PriceFeatDto(
              priceFeat.get(0),
              priceFeat.get(1),
              priceFeat.get(2),
              priceFeat.get(3)
      );
      priceFeatDtos.add(priceFeatDto);
    }

    ExpertBoardDto expertBoardDto = new ExpertBoardDto(
            expertBoardRequest.getTitle(),
            expertBoardRequest.getSPrice(),
            expertBoardRequest.getDPrice(),
            expertBoardRequest.getPPrice(),
            expertBoardRequest.getType(),
            expertBoardRequest.getContent(),
            thumbnail,
            priceFeatDtos
    );

    expertBoardService.updateExpertBoard(id, expertBoardDto);
  }

  @GetMapping("/check-reservation/{expertBoardId}")
  public List<ReservationDto> checkReservation(@PathVariable("expertBoardId") Long expertBoardId){
    return expertBoardService.checkReservation(expertBoardId);
  }

  @GetMapping("/check-reservation-date/{expertBoardId}")
  public List<LocalDate> checkReservationDate(@PathVariable("expertBoardId") Long expertBoardId){
    return expertBoardService.checkReservationDate(expertBoardId);
  }

  @GetMapping("/all-board")
  public List<GetAllBoardDto> findAllExpertBoards(){
    return expertBoardService.findAllExpertBoards();
  }

  @GetMapping("/board/{id}")
  public EachBoardDto getBoard(@PathVariable("id") Long id){
    return expertBoardService.findById(id);
  }

  @DeleteMapping("/delete-board/{expertBoardId}")
  public void deleteBoard(@PathVariable("expertBoardId") Long expertBoardId){
    expertBoardService.deleteExpertBoardById(expertBoardId);
  }

  @PostMapping("/expert-comments")
  public void deleteComments(Long id){
    myPageService.deleteCommentById(id);
  }

  // 등록한 게시판이 있는지 확인
  @GetMapping("/check/{username}")
  public String check(@PathVariable String username){
    UserEntity user = userService.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("해당하는 유저가 없습니다."));
    if (user.getExpertBoard() == null){
      return "등록한 글이 없습니다";
    }
    else {
      return user.getExpertBoard().getId().toString();
    }
  }
}












