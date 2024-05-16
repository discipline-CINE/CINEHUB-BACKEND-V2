package Discipline.CineHub.service.actor;

import Discipline.CineHub.dto.actor.ActorDto;
import Discipline.CineHub.dto.actor.AllActorDto;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.entity.actor.ActorComment;
import Discipline.CineHub.entity.actor.QActor;
import Discipline.CineHub.repository.actor.ActorCommentRepository;
import Discipline.CineHub.repository.actor.ActorRepository;
import Discipline.CineHub.request.actor.ActorRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ActorService {
  @Autowired
  private JPAQueryFactory queryFactory;

  @Autowired
  private ActorRepository actorRepository;

  @Autowired
  private ActorCommentRepository actorCommentRepository;

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

  public List<AllActorDto> findAllActors(){
    List<Actor> actors = actorRepository.findAll();
    List<AllActorDto> allActorDtos = new ArrayList<>();

    for(Actor actor : actors){
      AllActorDto allActorDto = new AllActorDto(actor.getId(),
              actor.getName(), actor.getGender(),
              actor.getBirth(), actor.getHeight(),
              actor.getWeight(), actor.getThumbnailId(),
              actor.getUser().getUsername());

      allActorDtos.add(allActorDto);
    }

    return allActorDtos;
  }

  // 배우 조회 id를 활용하여
  public Optional<Actor> findById(Long id){return actorRepository.findById(id);}

  public Actor getById(Long id){return actorRepository.getById(id);}

  public AllActorDto getByUsername(String username){
    Actor actor = actorRepository.findByUser_Username(username);
    AllActorDto allActorDto = new AllActorDto(
            actor.getId(), actor.getName(), actor.getGender(),
            actor.getBirth(), actor.getHeight(), actor.getWeight(),
            actor.getThumbnailId(), actor.getUsername()
    );

    return allActorDto;
  }

  // 배우 댓글 저장
  public void postComment(ActorComment actorComment){
    actorCommentRepository.save(actorComment);
  }

  public List<Actor> searchActorsByName(String keyword) {
    QActor qActor = QActor.actor;

    BooleanExpression predicate = qActor.name.containsIgnoreCase(keyword);

    return queryFactory.selectFrom(qActor)
            .where(predicate)
            .fetch();
  }
}
