package Discipline.CineHub.service.expert;

import Discipline.CineHub.entity.expert.Expert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

public interface IExpertService {
  Expert addNewExpert(String expertType, String summary, MultipartFile photo) throws IOException, SQLException;
}
