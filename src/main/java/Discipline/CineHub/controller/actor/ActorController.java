package Discipline.CineHub.controller.actor;

import Discipline.CineHub.dto.actor.ActorDto;
import Discipline.CineHub.dto.actor.ThumbnailDto;
import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.entity.actor.GenderType;
import Discipline.CineHub.request.actor.ActorRequest;
import Discipline.CineHub.service.actor.ActorService;
import Discipline.CineHub.service.actor.StorageService;
import Discipline.CineHub.service.actor.ThumbnailService;
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

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class ActorController {
  private final ActorService actorService;
  private StorageService service;

  @Autowired
  public ActorController(ActorService actorService, StorageService service) {
    this.actorService = actorService;
    this.service = service;
  }

//  @PostMapping("/upload")
//  public ResponseEntity<URL> uploadFile(@RequestParam(value = "file") MultipartFile file) {
//    return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);
//  }

  //배우 삭제
  @PostMapping("/delete-actor")
  public void deleteActorById(Long id){
    actorService.deleteById(id);
  }

  @GetMapping("/all-actors")
  public List<Actor> findAllActors(){
    return actorService.findAllActors();
  }

  @PostMapping("/upload")
  public URL saveFormRequests(ActorRequest actorRequest) throws IOException{
    String name = actorRequest.getName();
    GenderType gender = actorRequest.getGender();
    Integer birth = actorRequest.getBirth();
    Double height = actorRequest.getHeight();
    Double weight = actorRequest.getWeight();
    String specialty = actorRequest.getSpecialty();
    String career = actorRequest.getCareer();
    String content = actorRequest.getContent();
    MultipartFile file = actorRequest.getFile();

    URL thumbnailId = service.uploadFile(file);

    ActorDto actorDto = ActorDto.builder()
            .name(name)
            .gender(gender)
            .birth(birth)
            .height(height)
            .weight(weight)
            .specialty(specialty)
            .career(career)
            .content(content)
            .thumbnailId(thumbnailId)
            .build();

    return thumbnailId;
  }
}
