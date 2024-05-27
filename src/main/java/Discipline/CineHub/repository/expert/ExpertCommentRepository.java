package Discipline.CineHub.repository.expert;

import Discipline.CineHub.entity.expert.ExpertBoard;
import Discipline.CineHub.entity.expert.ExpertComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
public interface ExpertCommentRepository extends
        JpaRepository<ExpertComment,Long>,
        QuerydslPredicateExecutor<ExpertComment>
{
  @Modifying
  @Transactional
  @Query("delete from ExpertComment e where e.id = :id")
  void deleteByIdCustom(Long id);
}
