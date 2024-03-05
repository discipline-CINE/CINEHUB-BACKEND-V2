package Discipline.CineHub.response.expert;

import java.time.LocalDate;

public class BookingResponse {
  private Long id;
  private LocalDate checkInDate;
  private LocalDate checkOutDate;
  private String guestName;
  private String guestEmail;
  private String bookingConfirmationCode;
  private ExpertResponse expert;

  public BookingResponse(Long id, LocalDate checkInDate,
                         LocalDate checkOutDate, String bookingConfirmationCode){
    this.id = id;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.bookingConfirmationCode = bookingConfirmationCode;
  }
}
