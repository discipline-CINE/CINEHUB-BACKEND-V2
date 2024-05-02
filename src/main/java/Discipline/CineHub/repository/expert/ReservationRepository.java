package Discipline.CineHub.repository.expert;

import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.expert.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  Optional<Reservation> findById(Long id);
  List<Reservation> findByUser(UserEntity user);
}
