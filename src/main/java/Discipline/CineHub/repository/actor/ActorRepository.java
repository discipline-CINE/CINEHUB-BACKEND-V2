package Discipline.CineHub.repository.actor;

import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.entity.actor.QActor;
import Discipline.CineHub.entity.external.RecommendationEntity;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ActorRepository extends
        JpaRepository<Actor, Long>,
        QuerydslPredicateExecutor<Actor>,
        QuerydslBinderCustomizer<QActor>
{
  @Transactional
  void deleteActorById(Long id);

  @Transactional
  void deleteActorByName(String name);

  @Override
  default void customize(QuerydslBindings bindings, QActor root){
    bindings.excludeUnlistedProperties(true);
    bindings.including(root.name, root.gender);
    bindings.bind(root.name).first(StringExpression::containsIgnoreCase);
    bindings.bind(root.gender).first((path, value) -> path.eq(value));
  }

  Optional<Actor> findById(Long id);

  Optional<Actor> findByUsername(String username);

  Actor findByUser_Username(String username);
  Actor findByThumbnailId(URL thumbnailId); // 새로운 메소드 추가

  @Modifying
  @Query("DELETE FROM Actor a WHERE a.username = :username")
  void deleteActorByUsername(String username);
}
