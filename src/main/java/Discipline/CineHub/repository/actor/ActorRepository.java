package Discipline.CineHub.repository.actor;

import Discipline.CineHub.entity.actor.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RepositoryRestResource
public interface ActorRepository extends
        JpaRepository<Actor, Long>, QuerydslPredicateExecutor<Actor>
{
  @Transactional
  void deleteActorById(Long id);

  @Transactional
  void deleteActorByName(String name);

  Optional<Actor> findById(Long id);

  Actor findByUser_Username(String username);
}
