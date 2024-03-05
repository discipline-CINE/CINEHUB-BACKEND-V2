package Discipline.CineHub.repository.expert;

import Discipline.CineHub.entity.expert.BookedExpert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookedExpert, Long> {
  List<BookedExpert> findByExpertId(Long expertId);
}
