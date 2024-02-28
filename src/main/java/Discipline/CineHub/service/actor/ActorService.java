package Discipline.CineHub.service.actor;

import Discipline.CineHub.dto.actor.ActorDto;
import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.repository.actor.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ActorService {
  @Autowired
  private ActorRepository actorRepository;

  //Id로 배우 삭제
  @Transactional
  public void deleteById(Long id){
    actorRepository.deleteActorById(id);
  }

  //배우 추가
  @Transactional
  public Long save(ActorDto actorDto){
    return actorRepository.save(actorDto.toEntity()).getId();
  }

  //모든 배우 조회
  public List<Actor> findAllActors(){
    return actorRepository.findAll();
  }
}
