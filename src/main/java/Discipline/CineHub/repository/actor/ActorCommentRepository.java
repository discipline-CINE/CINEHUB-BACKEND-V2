package Discipline.CineHub.repository.actor;

import Discipline.CineHub.entity.actor.ActorComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
public interface ActorCommentRepository extends
        JpaRepository<ActorComment, Long>, QuerydslPredicateExecutor<ActorComment> {
  @Modifying
  @Transactional
  @Query("delete from ActorComment e where e.id = :id")
  void deleteByIdCustom(Long id);
}
