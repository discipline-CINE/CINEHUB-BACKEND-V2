package com.Discipline.cinehub.service.user;

import com.Discipline.cinehub.entity.AuthorityEntity;
import com.Discipline.cinehub.entity.UserEntity;
import com.Discipline.cinehub.exception.BusinessLogicException;
import com.Discipline.cinehub.model.EmailVerificationResult;
import com.Discipline.cinehub.repository.UserRepository;
import com.Discipline.cinehub.dto.UserDto;
import com.Discipline.cinehub.exception.ExceptionCode;

import com.Discipline.cinehub.service.authentication.MailService;
import com.Discipline.cinehub.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private static final String AUTH_CODE_PREFIX = "AuthCode ";
    private final UserRepository userRepository;
    private final MailService mailService;
    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;

    public UserEntity signup(UserDto userDto) {

        AuthorityEntity authority = AuthorityEntity.builder()
                .authorityName("USER")
                .build();
        UserEntity user = UserEntity.builder()
                .username(userDto.getUsername())
                .address(userDto.getAddress())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .phonenumber(userDto.getPhonenumber())
                .authorities(Collections.singleton(authority))
                .build();

        return userRepository.save(user);
    }

    public void sendCodeToEmail(String toEmail) {
        this.checkDuplicatedEmail(toEmail);
        String title = "Cinehub 이메일 인증 번호";
        String authCode = this.createCode();
        mailService.sendEmail(toEmail, title, authCode);
        redisService.setValues(AUTH_CODE_PREFIX + toEmail, authCode, Duration.ofMillis(this.authCodeExpirationMillis));
    }

    public void sendCodeToSms(String toSms) {

    }

    private void checkDuplicatedEmail(String email) {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            log.debug("UserServiceImpl.checkDuplicatedEmail exception occur email: {}", email);
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
        }
    }

    private void checkDuplicatedPhonenumber(String phonenumber) {
        Optional<UserEntity> user = userRepository.findByPhonenumber(phonenumber);
        if (user.isPresent()) {
            log.debug("UserServiceImpl.checkDuplicatedPhonenumber exception occur phonenumber: {}", phonenumber);
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
        }
    }

    private String createCode() {
        int length = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("UserService.createCode() exception occur");
            throw new BusinessLogicException(ExceptionCode.NO_SUCH_ALGORITHM);
        }
    }

    public EmailVerificationResult verifiedCode(String email, String authCode) {
        this.checkDuplicatedEmail(email);
        String redisAuthCode = redisService.getValues(AUTH_CODE_PREFIX + email);
        boolean authResult = redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode);

        return EmailVerificationResult.of(authResult);
    }
}
