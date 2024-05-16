package Discipline.CineHub.repository.actor;

import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.entity.actor.QActor;
import com.querydsl.core.types.dsl.StringExpression;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
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
    bindings.including(root.name);
    bindings.bind(root.name).first(StringExpression::containsIgnoreCase);
  }

  Optional<Actor> findById(Long id);

  Actor findByUser_Username(String username);
}
