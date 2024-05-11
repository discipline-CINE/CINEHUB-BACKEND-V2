package Discipline.CineHub.controller.mypage;

import Discipline.CineHub.dto.UserDto;
import Discipline.CineHub.dto.expert.ExpertBoardIdTitleUsername;
import Discipline.CineHub.dto.expert.ExpertCommentDto;
import Discipline.CineHub.dto.expert.ReservationDto;
import Discipline.CineHub.dto.expert.ReservationDtoByUser;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.expert.ExpertBoard;
import Discipline.CineHub.service.expert.ExpertBoardService;
import Discipline.CineHub.service.mypage.MyPageService;
import Discipline.CineHub.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/my-page")
@RestController
public class MyPageController {
  @Autowired
  UserService userService;

  @Autowired
  MyPageService myPageService;

  @Autowired
  ExpertBoardService expertBoardService;

  // 마이페이지 : 유저(자신의) 정보 가져오기
  @GetMapping("/user-info")
  public UserDto getUserInfo(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication.getPrincipal() == null) {
      new RuntimeException("마이페이지 오류입니다.");
    }
    Optional<UserEntity> user =  userService.findByUsername(authentication.getName());
    UserDto userDto = new UserDto();

    userDto.setUsername(user.get().getUsername());
    userDto.setPassword(user.get().getPassword());
    userDto.setAddress(user.get().getAddress());
    userDto.setEmail(user.get().getEmail());
    userDto.setProvider(user.get().getProvider());
    userDto.setPhonenumber(user.get().getPhonenumber());

    return userDto;
  }

  // 마이페이지 : 일반 유저에서 전문가 유저로 역할 변경
  @PostMapping("/be-expert")
  public ResponseEntity<String> beExpert(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication.getPrincipal() == null) {
      new RuntimeException("마이페이지 오류입니다.");
    }
    userService.changeToExpert(authentication.getName());

    return ResponseEntity.ok("전문가가 되었습니다");
  }

  // 마이페이지 : 유저의 ROLE 확인
  @GetMapping("/my-role")
  public String checkMyRole(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication.getPrincipal() == null) {
      new RuntimeException("마이페이지 오류입니다.");
    }
    return userService.checkMyRole(authentication.getName());
  }

  // 마에페이지 : 전문가 - 예약 조회
  @GetMapping("/check-reservation-expert")
  public List<ReservationDto> checkReservationByExp(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication.getPrincipal() == null) {
      new RuntimeException("마이페이지 오류입니다.");
    }
      return myPageService.checkReservation(authentication.getName());
  }

  // 마이페이지 : 일반 - 예약 조회
  @GetMapping("/check-reservation-user")
  public List<ReservationDtoByUser> checkReservationByUser(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication.getPrincipal() == null) {
      new RuntimeException("마이페이지 오류입니다.");
    }
    return myPageService.checkReservationByUser(authentication.getName());
  }

  // 완료된 전문가 서비스 조회 - 의뢰인
  @GetMapping("/check-complete-service")
  public List<ExpertBoardIdTitleUsername> checkCompleteService(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication.getPrincipal() == null) {
      new RuntimeException("마이페이지 오류입니다.");
    }
    return myPageService.checkComplete(authentication.getName());
  }

  // 댓글 등록 - 의뢰인
  @PostMapping("/expert-comment")
  public String expertComment(String comment, Long expertBoardId){
    ExpertCommentDto expertCommentDto = new ExpertCommentDto();

    ExpertBoard expertBoard = expertBoardService.getById(expertBoardId);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Optional<UserEntity> user = userService.findByUsername(authentication.getName());

    expertCommentDto.setComment(comment);
    expertCommentDto.setExpertBoard(expertBoard);
    expertCommentDto.setUser(user.get());

    return myPageService.expertComment(expertCommentDto);
  }
}


























