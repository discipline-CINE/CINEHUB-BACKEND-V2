package Discipline.CineHub.service.expert;

import Discipline.CineHub.entity.expert.Expert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IExpertService {
  Expert addNewExpert(String expertType, String summary, MultipartFile photo) throws IOException, SQLException;

  List<String> getAllExpertTypes();

  List<Expert> getAllExperts();

  byte[] getExpertPhotoByExpertId(Long expertId) throws SQLException;

  void deleteExpert(Long expertId);

  Expert updateExpert(long expertId, String expertType, String summary, byte[] photoBytes);

  Optional<Expert> getExpertById(Long expertId);

  List<Expert> getAvailableExperts(LocalDate checkInDate, LocalDate checkOutDate, String expertType);
}
