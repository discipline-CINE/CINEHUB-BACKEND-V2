package Discipline.CineHub.controller.expert;

import Discipline.CineHub.entity.expert.BookedExpert;
import Discipline.CineHub.entity.expert.Expert;
import Discipline.CineHub.response.expert.BookingResponse;
import Discipline.CineHub.response.expert.ExpertResponse;
import Discipline.CineHub.service.expert.BookingService;
import Discipline.CineHub.service.expert.IExpertService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expert")
public class ExpertController {

  private final IExpertService expertService;
  private final BookingService bookingService;

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

  // 전문가의 모든 타입 가져오는 기능
  @GetMapping("/get-types")
  public List<String> getExpertTypes() {return expertService.getAllExpertTypes();}

  // 모든 전문가 찾기
  @GetMapping("/all-experts")
  public ResponseEntity<List<ExpertResponse>> getAllExperts() throws SQLException {
    List<Expert> experts = expertService.getAllExperts();
    List<ExpertResponse> expertResponses = new ArrayList<>();

    for(Expert expert : experts){
      byte[] photoBytes = expertService.getExpertPhotoByExpertId(expert.getId());
      if(photoBytes != null && photoBytes.length > 0){
        String base64Photo = Base64.encodeBase64String(photoBytes);
        ExpertResponse expertResponse = getExpertResponse(expert);
        expertResponse.setPhoto(base64Photo);
        expertResponses.add(expertResponse);
      }
    }
    return ResponseEntity.ok(expertResponses);
  }

  // 전문가 상태 가져오기
  private ExpertResponse getExpertResponse(Expert expert){
    List<BookedExpert> bookings = getAllBookingsByExpertId(expert.getId());
    List<BookingResponse> bookingInfo = bookings
            .stream()
            .map(booking -> new BookingResponse(
                    booking.getBookingId(),
                    booking.getCheckInDate(),
                    booking.getCheckOutDate(),
                    booking.getBookingConfirmationCode())).toList();
    byte[] photoBytes = null;
    Blob photoBlob = expert.getThumbnail();

    if(photoBlob != null){
      try {
        photoBytes = photoBlob.getBytes(1, (int)photoBlob.length());
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    return new ExpertResponse(
            expert.getId(), expert.getExpertType(), expert.getSummary(),
            photoBytes, expert.isBooked(), bookingInfo);
  }

  // expertId로 예약된 전문가들 가져오기
  private List<BookedExpert> getAllBookingsByExpertId(Long expertId){
    return bookingService.getAllBookingsByExpertId(expertId);
  }
}














