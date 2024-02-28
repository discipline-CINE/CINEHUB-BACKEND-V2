package Discipline.CineHub.repository.actor;

import Discipline.CineHub.entity.actor.ActorComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ActorCommentRepository extends
        JpaRepository<ActorComment, Long>, QuerydslPredicateExecutor<ActorComment> {
}
