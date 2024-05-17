package Discipline.CineHub.repository.expert;

import Discipline.CineHub.entity.expert.ExpertBoard;
import Discipline.CineHub.entity.expert.ExpertComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ExpertCommentRepository extends
        JpaRepository<ExpertComment,Long>,
        QuerydslPredicateExecutor<ExpertComment>
{

}
