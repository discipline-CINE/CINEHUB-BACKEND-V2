package com.Discipline.cinehub.service.login;

import com.Discipline.cinehub.entity.UserEntity;
import com.Discipline.cinehub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    // JPA 사용. Mybatis 사용시 mapper 등록 후 user 정보 받아오기
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity entity = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("username not found"));

        return entity;
    }
}
