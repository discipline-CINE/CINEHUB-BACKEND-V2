package Discipline.CineHub.controller.expert;

import Discipline.CineHub.entity.expert.Expert;
import Discipline.CineHub.response.expert.ExpertResponse;
import Discipline.CineHub.service.expert.IExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expert")
public class ExpertController {

  private final IExpertService expertService;

  // 전문가 타입, 소개, 사진으로 전문가 추가
  @PostMapping("/add/new-expert")
  public ResponseEntity<ExpertResponse> addNewExpert(
          @RequestParam("expertType") String expertType,
          @RequestParam("summary") String summary,
          @RequestParam("photo")MultipartFile photo
          ) throws SQLException, IOException {
    Expert savedExpert = expertService.addNewExpert(expertType, summary, photo);
    ExpertResponse response = new ExpertResponse(savedExpert.getId(), savedExpert.getExpertType(), savedExpert.getSummary());
    return ResponseEntity.ok(response);
  }
}
