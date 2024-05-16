package Discipline.CineHub.repository.expert;

import Discipline.CineHub.entity.actor.Actor;
import Discipline.CineHub.entity.actor.QActor;
import Discipline.CineHub.entity.expert.ExpertBoard;
import Discipline.CineHub.entity.expert.QExpertBoard;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ExpertBoardRepository extends
        JpaRepository<ExpertBoard, Long>,
        QuerydslPredicateExecutor<ExpertBoard>,
        QuerydslBinderCustomizer<QExpertBoard>
{
  Optional<ExpertBoard> findById(Long id);

  List<ExpertBoard> findAll();

  @Override
  default void customize(QuerydslBindings bindings, QExpertBoard root){
    bindings.excludeUnlistedProperties(true);
    bindings.including(root.title);
    bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
  }
}