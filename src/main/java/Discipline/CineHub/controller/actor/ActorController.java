package Discipline.CineHub.controller.actor;

import Discipline.CineHub.dto.actor.ActorDto;
import Discipline.CineHub.dto.actor.ThumbnailDto;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.entity.actor.ActorRecommendationDto;
import Discipline.CineHub.entity.actor.GenderType;
import Discipline.CineHub.request.actor.ActorRequest;
import Discipline.CineHub.service.actor.ActorService;
import Discipline.CineHub.service.actor.StorageService;
import Discipline.CineHub.service.actor.ThumbnailService;
import Discipline.CineHub.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/actor")
public class ActorController {
  private final ActorService actorService;
  private StorageService service;
  private UserService userService;

  @Autowired
  public ActorController(ActorService actorService, StorageService service, UserService userService) {
    this.actorService = actorService;
    this.service = service;
    this.userService = userService;
  }

  // 배우 삭제
  @DeleteMapping("/delete-actor")
  public void deleteActorById(Long id){
    actorService.deleteById(id);
  }

  // 모든 배우 조회
  @GetMapping("/all-actors")
  public List<Actor> findAllActors(){
    return actorService.findAllActors();
  }

  // 배우 수정
  @PutMapping("/update-actor/{id}")
  public Optional<Actor> updateActorById(@PathVariable Long id, ActorRequest actorRequest){
    Optional<Actor> actor = getActorById(id);

    String name = actorRequest.getName();
    GenderType gender = actorRequest.getGender();
    Integer birth = actorRequest.getBirth();
    Double height = actorRequest.getHeight();
    Double weight = actorRequest.getWeight();
    String content = actorRequest.getContent();
    String sns = actorRequest.getSns();
    MultipartFile file = actorRequest.getFile();

    URL thumbnailId = service.uploadFile(file);

    ActorDto actorDto = ActorDto.builder()
            .name(name)
            .gender(gender)
            .birth(birth)
            .height(height)
            .weight(weight)
            .content(content)
            .sns(sns)
            .thumbnailId(thumbnailId)
            .build();
    actorService.save(actorDto);

    return actor;
  }

  //배우 등록
  @PostMapping("/upload")
  public URL saveFormRequests(String username,ActorRequest actorRequest) throws IOException{
    String name = actorRequest.getName();
    GenderType gender = actorRequest.getGender();
    Integer birth = actorRequest.getBirth();
    Double height = actorRequest.getHeight();
    Double weight = actorRequest.getWeight();
    String content = actorRequest.getContent();
    String sns = actorRequest.getSns();
    MultipartFile file = actorRequest.getFile();

    URL thumbnailId = service.uploadFile(file);

    ActorDto actorDto = ActorDto.builder()
            .name(name)
            .gender(gender)
            .birth(birth)
            .height(height)
            .weight(weight)
            .content(content)
            .sns(sns)
            .thumbnailId(thumbnailId)
            .build();
    Long actorId = actorService.save(actorDto);
    userService.connectUserAndActor(actorId, username);

    return thumbnailId;
  }

  @GetMapping("/find-Actor/{username}")
  public ActorRecommendationDto findByUsername(@PathVariable String username) {
    return actorService.getActorWithRecommendations(username);
  }


  // ID로 배우 정보 가져오기
  public Optional<Actor> getActorById(Long id){
    return actorService.findById(id);
  }
}
