package Discipline.CineHub.controller.actor;

import Discipline.CineHub.dto.actor.ActorDto;
import Discipline.CineHub.dto.actor.ThumbnailDto;
import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.entity.actor.GenderType;
import Discipline.CineHub.request.actor.ActorRequest;
import Discipline.CineHub.service.actor.ActorService;
import Discipline.CineHub.service.actor.ThumbnailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class ActorController {
  private final ActorService actorService;
  private final ThumbnailService thumbnailService;

  //배우 삭제
  @PostMapping("/delete-actor")
  public void deleteActorById(Long id){
    actorService.deleteById(id);
  }

  //업로드 된 이미지가 저장될 장소 application.yaml에서 설정 가능
  @Value("${image.path}")
  private String uploadDir;

  @GetMapping("/all-actors")
  public List<Actor> findAllActors(){
    return actorService.findAllActors();
  }

  @PostMapping("/actor")
  public void saveFormRequests(ActorRequest actorRequest) throws IOException {
    String name = actorRequest.getName();
    GenderType gender = actorRequest.getGender();
    Integer birth = actorRequest.getBirth();
    Double height = actorRequest.getHeight();
    Double weight = actorRequest.getWeight();
    String specialty = actorRequest.getSpecialty();
    String career = actorRequest.getCareer();
    String content = actorRequest.getContent();
    ActorDto actorDto = ActorDto.builder()
            .name(name)
            .gender(gender)
            .birth(birth)
            .height(height)
            .weight(weight)
            .specialty(specialty)
            .career(career)
            .content(content)
            .build();

    if(actorRequest.getFile() != null){
      MultipartFile file = actorRequest.getFile();
      String fullPath = uploadDir + file.getOriginalFilename();
      file.transferTo(new File(fullPath));
      log.info("file.getOriginalFilename = {}", file.getOriginalFilename());
      log.info("fullPath = {}", fullPath);

      ThumbnailDto thumbnailDto = ThumbnailDto.builder()
              .originFileName(file.getOriginalFilename())
              .fullPath(uploadDir + file.getOriginalFilename())
              .build();
      Long savedFileId = thumbnailService.save(thumbnailDto);
      actorDto.setThumbnailId(savedFileId);
    }
    actorService.save(actorDto);
  }
}
