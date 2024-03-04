package Discipline.CineHub.response.expert;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.List;

@Data
@NoArgsConstructor
public class ExpertResponse {
  private Long id;
  private String expertType;
  private String summary;
  private String photo;
  private boolean isBooked;
  private List<BookingResponse> bookings;

  public ExpertResponse(Long id, String expertType, String summary) {
    this.id = id;
    this.expertType = expertType;
    this.summary = summary;
  }

  public ExpertResponse(Long id, String expertType, String summary, byte[] photoBytes,
                        boolean isBooked, List<BookingResponse> bookings) {
    this.id = id;
    this.expertType = expertType;
    this.summary = summary;
    this.photo = photoBytes != null ? Base64.encodeBase64String(photoBytes) : null; // photoBytes가 null이 아니라면 문자열로 인코딩하여 저장
    this.isBooked = isBooked;
    this.bookings = bookings;
  }
}
