package Discipline.CineHub.security.authentication;

import Discipline.CineHub.dto.UserResponseDto;
import Discipline.CineHub.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.http.ResponseCookie;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    // final로 처리할 지 고민, 일단 문제 없음
    private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
    UserEntity user = (UserEntity) authentication.getPrincipal();

    UserResponseDto userResponseDto = new UserResponseDto(user);

    ResponseCookie cookie = ResponseCookie.from("JSESSIONID", request.getSession().getId())
            .path("/")
            .httpOnly(true)
            .secure(true)
            .sameSite("None")
            .build();

    response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

    response.setStatus(HttpStatus.OK.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    objectMapper.writeValue(response.getWriter(), userResponseDto);
  }
}
