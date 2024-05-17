package Discipline.CineHub.security.authentication;

import Discipline.CineHub.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// CustomAuthenticationFilter 이후의 로그인 프로세스 진행
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    // CustomUserDetailsService를 통해 DB의 User 정보 가져옴
    // AuthenticationToken에 저장된 사용자 정보와 일치하면 SuccessHandler, 실패하면 FailedHandler 실행
    // 각각 CustomAuthenticationSuccessHandler.java, CustomAuthenticationFailureHandler.java에 구현되어 있음
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String loginId = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserEntity entity = (UserEntity) userDetailsService.loadUserByUsername(loginId);

        if(!passwordEncoder.matches(password, entity.getPassword())) {
            throw new BadCredentialsException("Invaild Password");
        }

        return new CustomAuthenticationToken(entity, null, entity.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthenticationToken.class);
    }


}

