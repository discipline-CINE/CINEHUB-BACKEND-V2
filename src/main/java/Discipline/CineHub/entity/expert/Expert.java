package Discipline.CineHub.entity.expert;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Expert {
  @Id
  @GeneratedValue
  private Long id;
  private boolean isBooked = false;
  private String expertType;
  private @Size(max = 50000) @Column(nullable = false, length = 50000) String summary;
  @Lob private Blob thumbnail;
  @OneToMany(mappedBy = "expert", fetch = FetchType.LAZY, cascade = CascadeType.ALL) private List<BookedExpert> bookings;

  public Expert() {this.bookings = new ArrayList<>();}

  public void addBooking(BookedExpert booking){
    if (bookings == null){
      bookings = new ArrayList<>();
    }
    bookings.add(booking);
    booking.setExpert(this);
    isBooked = true;
    String bookingCode = RandomStringUtils.randomNumeric(10);
    booking.setBookingConfirmationCode(bookingCode);
  }
}
