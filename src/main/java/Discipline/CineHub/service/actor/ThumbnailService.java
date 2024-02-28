package Discipline.CineHub.service.actor;

import Discipline.CineHub.dto.actor.ThumbnailDto;
import Discipline.CineHub.repository.actor.ThumbnailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ThumbnailService {
  private final ThumbnailRepository thumbnailRepository;

  // 썸네일 저장
  @Transactional
  public Long save(ThumbnailDto thumbnailDto){
    return thumbnailRepository.save(thumbnailDto.toEntity()).getId();
  }
}
