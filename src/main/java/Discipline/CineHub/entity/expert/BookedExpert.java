package Discipline.CineHub.entity.expert;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookedExpert {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bookingId;
  @Column(name = "check_in") private LocalDate checkInDate;
  @Column(name = "check_out") private LocalDate checkOutDate;
  @Column(name = "email") private String email;
  @Column(name = "fullname") private String fullName;
  @Column(name = "confirmation_Code") private String bookingConfirmationCode;
  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "expert_id") private Expert expert;

  public void setBookingConfirmationCode(String bookingConfirmationCode){
    this.bookingConfirmationCode = bookingConfirmationCode;
  }
}
