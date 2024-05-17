// 사용자가 /api/login으로 로그인 요청시 해당 필터가 가로챔, 로그인 프로세스 시작
// 로그인 flow
// 1. CustomAuthenticationFilter에 적용된 URL으로 사용자 요청 발생 시 CustomAuthenticationFilter가 요청 가로챔
// 2. CustomAuthenticationToken 생성, AuthenticationManager에 인증 처리 요청. Token에 아이디와 패스워드 보관
// 3. AuthenticationManager는 AuthenticationProvider에 인증 처리 전달, CustomAuthenticationProvider.java에 구현됨
// 4. CustomAuthenticationProvider에서 CustomUserDetailsService를 통해 DB의 User 정보를 가져와서,
//    AuthenticationToken에 저장된 사용자 정보와 일치하는지 확인, 일치하면 SuccessHandler, 실패하면 FailureHandler 실행
//    에러 발생 시 FailureHandler 실행
// 5. 인증 성공 후 인가 확인. hasRole("USER")와 같은 경우 인가 확인
// 6. 인가 실패 시 경우에 따라, CustomLoginAuthenticationEntrypoint 또는 CustomAccessDeniedHandler 실행.
//    인증 성공 시 거치지 않음


package Discipline.CineHub.security.authentication;

import lombok.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;


public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    // final로 처리해야 할지 고민, 일단은 문제 없음
    private ObjectMapper objectMapper = new ObjectMapper();

    public CustomAuthenticationFilter() {
        // url과 일치하면 동작, 사용자가 /api/login으로 요청 시 CustomAuthenticationFilter가 요청 가로챔
        super(new AntPathRequestMatcher("/api/login"));
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException, IOException, ServletException {

        // POST 여부 확인, Post 아닐 시 IllegalStateException 발생
        if(!isPost(request)) {
            throw new IllegalStateException("Authentication is not supported");
        }

        AccountDto accountDto = objectMapper.readValue(request.getReader(), AccountDto.class);

        // ID, Password 있는지 확인, 빈 칸일 시 IllegalArgumentException 발생
        if(!StringUtils.hasLength(accountDto.getUsername()) || !StringUtils.hasLength(accountDto.getPassword())) {
            throw new IllegalArgumentException("username or password is empty");
        }

        // 인증되지 않은 상태의 CustomAuthenticationToken 생성, AuthenticationManager에게 인증 처리 요청
        // Token에 사용자가 전송한 아이디와 패스워드 보관함
        CustomAuthenticationToken token = new CustomAuthenticationToken(accountDto.getUsername(), accountDto.getPassword());

        // AuthenticationManager는 AuthenticationProvider에 인증 처리 전달, CustomAuthenticationProvider.java로 넘어감
        return getAuthenticationManager().authenticate(token);
    }

    private boolean isPost(HttpServletRequest request) {

        if("POST".equals(request.getMethod())) {
            return true;
        }

        return false;
    }

    @Data
    public static class AccountDto {
        private String username;
        private String password;
    }
}
