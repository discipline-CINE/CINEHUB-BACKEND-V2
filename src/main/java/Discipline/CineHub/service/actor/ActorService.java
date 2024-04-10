package Discipline.CineHub.service.actor;

import Discipline.CineHub.dto.actor.ActorDto;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.repository.actor.ActorRepository;
import Discipline.CineHub.request.actor.ActorRequest;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.List;
import java.util.Optional;

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

//  // 배우 조회 id를 활용하여
  public Optional<Actor> findById(Long id){return actorRepository.findById(id);}

  public Actor getById(Long id){return actorRepository.getById(id);}

}
