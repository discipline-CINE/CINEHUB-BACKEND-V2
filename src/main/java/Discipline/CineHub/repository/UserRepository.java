package Discipline.CineHub.repository;

import Discipline.CineHub.dto.UserDto;
import Discipline.CineHub.entity.UserEntity;
import Discipline.CineHub.entity.actor.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<UserEntity, Long>, QuerydslPredicateExecutor<UserEntity> {

    @Transactional
    Optional<UserEntity> findByUsername(String username);

    @Transactional
    Optional<UserEntity> findByEmail(String email);

    @Transactional
    Optional<UserEntity> findByPhonenumber(String phonenumber);
}
