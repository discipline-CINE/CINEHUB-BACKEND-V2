package com.Discipline.cinehub.service.login;

public interface SocialLogin {
    String getProvider(); // 소셜 로그인 provider 정보
    String getEmail(); // 소셜 로그인 이메일 정보
    String getUsername(); // 소셜 로그인 닉네임 정보
}
