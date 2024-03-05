package Discipline.CineHub.service.expert;

import Discipline.CineHub.entity.expert.BookedExpert;

import java.util.List;

public interface IBookingService {
  List<BookedExpert> getAllBookingsByExpertId(Long expertId);
}
