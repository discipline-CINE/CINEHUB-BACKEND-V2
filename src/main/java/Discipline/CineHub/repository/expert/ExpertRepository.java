package Discipline.CineHub.repository.expert;

import Discipline.CineHub.entity.expert.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ExpertRepository extends JpaRepository<Expert, Long> {
  // 맞춤형 쿼리를 작성
  // DISTINCT은 중복을 제거해준다. 예시로 a,a,b,b,c가 있다면 a,b,c만 나오는 기능이다.
  @Query("SELECT DISTINCT r.expertType from Expert r")
  List<String> findDistinctExpertTypes();

  @Query(" SELECT r FROM Expert r " +
          " WHERE r.expertType LIKE %:expertType% " +
          " AND r.id NOT IN (" +
          "  SELECT br.expert.id FROM BookedExpert br " +
          "  WHERE ((br.checkInDate <= :checkOutDate) AND (br.checkOutDate >= :checkInDate))" +
          ")")
  List<Expert> findAvailableExpertsByDatesAndType(LocalDate checkInDate, LocalDate checkOutDate, String expertType);
}
