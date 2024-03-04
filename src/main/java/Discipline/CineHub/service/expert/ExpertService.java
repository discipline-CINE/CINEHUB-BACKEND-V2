package Discipline.CineHub.service.expert;

import Discipline.CineHub.entity.expert.Expert;
import Discipline.CineHub.repository.expert.ExpertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertService implements IExpertService {
  private final ExpertRepository expertRepository;

  @Override
  public Expert addNewExpert(String expertType, String summary, MultipartFile photo) throws IOException, SQLException {
    Expert expert = new Expert();
    expert.setExpertType(expertType);
    expert.setSummary(summary);
    if(!photo.isEmpty()){
      byte[] photoBytes = photo.getBytes();
      Blob photoBlob = new SerialBlob(photoBytes);
      expert.setThumbnail(photoBlob);
    }

    return expertRepository.save(expert);
  }

  @Override
  public List<String> getAllExpertTypes() {
    return expertRepository.findDistinctExpertTypes();
  }
}
