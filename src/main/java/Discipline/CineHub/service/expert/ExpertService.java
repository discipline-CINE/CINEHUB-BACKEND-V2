package Discipline.CineHub.service.expert;

import Discipline.CineHub.entity.expert.Expert;
import Discipline.CineHub.exception.ResourceNotFoundException;
import Discipline.CineHub.repository.expert.ExpertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

  @Override
  public List<Expert> getAllExperts() {
    return expertRepository.findAll();
  }

  @Override
  public byte[] getExpertPhotoByExpertId(Long expertId) throws SQLException {
    Optional<Expert> theExpert = expertRepository.findById(expertId);
    if(theExpert.isEmpty()){
      throw new ResourceNotFoundException("전문가를 찾지 못하였습니다.");
    }
    Blob photoBlob = theExpert.get().getThumbnail();
    if(photoBlob != null){
      return photoBlob.getBytes(1,(int) photoBlob.length());
    }
    return null;
  }

  @Override
  public void deleteExpert(Long expertId) {
    Optional<Expert> theExpert = expertRepository.findById(expertId);
    if(theExpert.isPresent()){
      expertRepository.deleteById(expertId);
    }
  }

  @Override
  public Expert updateExpert(long expertId, String expertType, String summary, byte[] photoBytes) {
    Expert expert = expertRepository.findById(expertId).get();
    if(expertType != null) expert.setExpertType(expertType);
    if(summary != null) expert.setSummary(summary);
    if(photoBytes != null && photoBytes.length > 0){
      try{
        expert.setThumbnail(new SerialBlob(photoBytes));
      } catch (SerialException e) {
        throw new RuntimeException(e);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }
    return expertRepository.save(expert);
  }


}
