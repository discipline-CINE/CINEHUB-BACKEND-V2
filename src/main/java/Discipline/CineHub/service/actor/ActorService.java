package Discipline.CineHub.service.actor;

import Discipline.CineHub.dto.actor.ActorDto;
import Discipline.CineHub.dto.actor.ActorRecommendationDto;
import Discipline.CineHub.dto.actor.AllActorDto;
import Discipline.CineHub.dto.external.RecommendationDto;
import Discipline.CineHub.dto.external.RecommendationResponse;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.entity.actor.ActorComment;
import Discipline.CineHub.entity.actor.QActor;
import Discipline.CineHub.entity.external.RecommendationEntity;
import Discipline.CineHub.repository.actor.ActorCommentRepository;
import Discipline.CineHub.repository.actor.ActorRepository;
import Discipline.CineHub.repository.external.RecommendationRepository;
import Discipline.CineHub.request.actor.ActorRequest;
import Discipline.CineHub.service.external.RecommendationService;
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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ActorService {
  @Autowired
  private JPAQueryFactory queryFactory;

  @Autowired
  private ActorRepository actorRepository;

  @Autowired
  private ActorCommentRepository actorCommentRepository;

  @Autowired
  private RecommendationService recommendationService;

  @Autowired
  private RecommendationRepository recommendationRepository;

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
              actor.getUser().getUsername(),
              new ArrayList<>()
      );

      allActorDtos.add(allActorDto);
    }

    return allActorDtos;
  }

  // 배우 조회 id를 활용하여
  public Optional<Actor> findById(Long id){return actorRepository.findById(id);}

  public Actor getById(Long id){return actorRepository.getById(id);}
/*

  public AllActorDto getByUsername(String username){
    Actor actor = actorRepository.findByUser_Username(username);
    AllActorDto allActorDto = new AllActorDto(
            actor.getId(), actor.getName(), actor.getGender(),
            actor.getBirth(), actor.getHeight(), actor.getWeight(),
            actor.getThumbnailId(), actor.getUsername(), null
    );

    return allActorDto;
  }

  // 특정 thumbnailId에 대한 recommendation 목록을 가져와서 AllActorDto로 변환하는 메소드
  public AllActorDto getActorWithRecommendations(URL thumbnailId) {
    // thumbnailId와 일치하는 actor 목록을 조회
    List<Actor> recommendations = actorRepository.findByThumbnailId(thumbnailId);

    // Recommendation 객체를 RecommendationResponse DTO로 변환
    List<RecommendationResponse> recommendationResponses = recommendations.stream()
            .map(recommendation -> new RecommendationResponse(recommendation.getId(), recommendation.getThumbnailId()))
            .collect(Collectors.toList());

    AllActorDto actorDto = new AllActorDto();
    actorDto.setRecommendationUrls(recommendationResponses);

    return actorDto;
  }
*/

  public AllActorDto getByUsernameWithRecommendations(String username) {
    // username으로 Actor 조회
    Actor actor = actorRepository.findByUser_Username(username);
    if (actor == null) {
      // Actor를 찾지 못한 경우 처리 (예: 예외 발생)
      throw new RuntimeException("Actor not found with username: " + username);
    }

    // Actor의 thumbnailId로 추천 목록 조회
    URL thumbnailUrl = actor.getThumbnailId();
    String thumbnailFileName = thumbnailUrl.toString().replace("https://discipline-actor.s3.ap-northeast-2.amazonaws.com/", "");

    List<RecommendationEntity> recommendations = recommendationRepository.findByInputImage(thumbnailFileName);
    

    // Recommendation 객체를 RecommendationResponse DTO로 변환
    List<RecommendationResponse> recommendationResponses = recommendations.stream()
            .map(recommendation -> {
              try {
                // 추천 URL을 URL 객체로 변환
                URL recommendationUrl = new URL(recommendation.getUrl());
                // 추천 URL을 thumbnailId로 가지고 있는 Actor 조회
                Actor recommendedActor = actorRepository.findByThumbnailId(recommendationUrl);
                if (recommendedActor != null) {
                  return new RecommendationResponse(recommendedActor.getId(), recommendedActor.getUsername(), recommendedActor.getName(), recommendationUrl.toString());
                } else {
                  return new RecommendationResponse(-1L, null, null, recommendationUrl.toString()); // 추천 배우가 없으면 -1 ID로 설정
                }
              } catch (MalformedURLException e) {
                throw new RuntimeException("Invalid URL format: " + recommendation.getUrl(), e);
              }
            })
            .collect(Collectors.toList());

    // Actor 정보와 추천 목록을 AllActorDto에 설정
    AllActorDto allActorDto = new AllActorDto(
            actor.getId(), actor.getName(), actor.getGender(),
            actor.getBirth(), actor.getHeight(), actor.getWeight(),
            actor.getThumbnailId(), actor.getUsername(), recommendationResponses
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
