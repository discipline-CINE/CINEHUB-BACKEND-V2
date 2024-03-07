package com.Discipline.cinehub.repository;

import com.Discipline.cinehub.dto.UserDto;
import com.Discipline.cinehub.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPhonenumber(String phonenumber);
}
