package Discipline.CineHub.service.expert;

import Discipline.CineHub.entity.expert.BookedExpert;
import Discipline.CineHub.repository.expert.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService{
  private final BookingRepository bookingRepository;
  private final IExpertService expertService;


  @Override
  public List<BookedExpert> getAllBookingsByExpertId(Long expertId) {
    return bookingRepository.findByExpertId(expertId);
  }
}
