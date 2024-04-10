package Discipline.CineHub.controller.user;

import Discipline.CineHub.annotation.CustomEmail;
import Discipline.CineHub.dto.SingleResponseDto;
import Discipline.CineHub.dto.UserDto;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.model.EmailVerificationResult;
import Discipline.CineHub.service.user.UserService;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;



@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signup(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userService.signup(userDto), HttpStatus.OK);
    }

    // 유저 정보 가져오기
    @PostMapping("/user")
    public ResponseEntity<String> getMyUserInfo() {
        return ResponseEntity.ok("user");
    }


    @PostMapping("/user/{username}")
    public ResponseEntity<String> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok("admin");
    }

    @PostMapping("/emails/verification-requests")
    public ResponseEntity sendMessage(@RequestParam("email") @Valid @CustomEmail String email) {
        userService.sendCodeToEmail(email);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/emails/verifications")
    public ResponseEntity verificationEmail(@RequestParam("email") @Valid @CustomEmail String email,
                                            @RequestParam("code") String authCode) {
        EmailVerificationResult response = userService.verifiedCode(email, authCode);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/find-actor/{id}")
    public String findActorById(@PathVariable Long id){
      return userService.connectUserAndActor(id);
    }
}
