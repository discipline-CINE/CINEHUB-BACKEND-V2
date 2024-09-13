package Discipline.CineHub.controller.mypage;

import Discipline.CineHub.dto.UserDto;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RequestMapping("/my-page")
@RestController
public class MyPageController {
  @Autowired
  UserService userService;

  // 마이페이지 : 유저(자신의) 정보 가져오기
  @GetMapping("/user-info/{username}")
  public UserDto getUserInfo(@PathVariable("username") String username){
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Optional<UserEntity> user =  userService.findByUsername(username);

    if (user == null) {
      new RuntimeException("유저가 없습니다.");
    }

    UserDto userDto = new UserDto();

    userDto.setUsername(user.get().getUsername());
    userDto.setPassword(user.get().getPassword());
    userDto.setAddress(user.get().getAddress());
    userDto.setEmail(user.get().getEmail());
    userDto.setProvider(user.get().getProvider());
    userDto.setPhonenumber(user.get().getPhonenumber());
    userDto.setRole(user.get().getRole());

    return userDto;
  }

  // 마이페이지 : 유저의 ROLE 확인
  @GetMapping("/my-role/{username}")
  public String checkMyRole(@PathVariable("username") String username){
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    if (authentication == null || authentication.getPrincipal() == null) {
//      new RuntimeException("마이페이지 오류입니다.");
//    }
    return userService.checkMyRole(username);
  }
}


























