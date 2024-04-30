package Discipline.CineHub.repository.expert;

import Discipline.CineHub.entity.expert.ExpertBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpertBoardRepository extends JpaRepository<ExpertBoard, Long> {
  Optional<ExpertBoard> findById(Long id);
}
