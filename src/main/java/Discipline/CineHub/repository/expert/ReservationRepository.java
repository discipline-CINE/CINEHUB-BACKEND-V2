package Discipline.CineHub.repository.expert;

import Discipline.CineHub.entity.expert.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
