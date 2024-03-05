package Discipline.CineHub.controller.expert;

import Discipline.CineHub.entity.expert.BookedExpert;
import Discipline.CineHub.entity.expert.Expert;
import Discipline.CineHub.response.expert.BookingResponse;
import Discipline.CineHub.response.expert.ExpertResponse;
import Discipline.CineHub.service.expert.BookingService;
import Discipline.CineHub.service.expert.IExpertService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
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

  // 전문가 삭제 expertId를 이용
  @DeleteMapping("/delete/{expertId}")
  public ResponseEntity<Void> deleteExpert(@PathVariable Long expertId){
    expertService.deleteExpert(expertId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // 전문가 업데이트
  @PutMapping("/update/{expertId}")
  public ResponseEntity<ExpertResponse> updateExpert(
          @PathVariable Long expertId,
          @RequestParam(required = false) String expertType,
          @RequestParam(required = false) String summary,
          @RequestParam(required = false) MultipartFile photo
  ) throws SQLException, IOException {
    // photyoBytes가 존재한다면 RequestParam에서 받은 정보를, 없다면 기존의 내용을 가져온다
    byte[] photoBytes = photo != null && !photo.isEmpty() ? photo.getBytes() : expertService.getExpertPhotoByExpertId(expertId);
    Blob photoBlob = photoBytes != null && photoBytes.length > 0 ? new SerialBlob(photoBytes) : null;
    Expert theExpert = expertService.updateExpert(expertId, expertType, summary, photoBytes);
    theExpert.setThumbnail(photoBlob);
    ExpertResponse expertResponse = getExpertResponse(theExpert);
    return ResponseEntity.ok(expertResponse);
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














