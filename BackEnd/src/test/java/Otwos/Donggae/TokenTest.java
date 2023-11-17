package Otwos.Donggae;


import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserInterestField;
import Otwos.Donggae.Jwt.CustomAuthenticationException;
import Otwos.Donggae.Jwt.JwtTokenProvider;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.member.repository.info.UserInterestFieldRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class TokenTest {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;    // 3

    private final JwtTokenProvider invalidSecretKeyJwtTokenProvider
            = new JwtTokenProvider(
            "invalidSecretKeyInvalidSecretKeyInvalidSecretKeyInvalidSecretKey"
    );    // 4

    @Test
    @DisplayName("토큰이 올바르게 생성된다.")
        // 5
    void createToken() {
        final String payload = String.valueOf(1);

        final String token = jwtTokenProvider.createToken(payload);

        System.out.println("!!token = " + token);

        assertThat(token).isNotNull();
    }

    @DisplayName("올바른 토큰 정보로 payload를 조회한다.")    // 6
    @Test
    void getPayloadByValidToken() {
        final String payload = String.valueOf(1);

        final String token = jwtTokenProvider.createToken(payload);

        assertThat(jwtTokenProvider.parsePayload(token)).isEqualTo(payload);

        jwtTokenProvider.validateToken(token);
    }

    @DisplayName("유효하지 않은 토큰 형식의 토큰으로 payload를 조회할 경우 예외를 발생시킨다.")    // 7
    @Test
    void getPayloadByInvalidToken() {
        assertThatExceptionOfType(CustomAuthenticationException.class)
                .isThrownBy(() -> jwtTokenProvider.parsePayload(null));
    }

    @DisplayName("만료된 토큰으로 payload를 조회할 경우 예외를 발생시킨다.")
    @Test
    void getPayloadByExpiredToken() {
        final String expiredToken = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .setSubject(String.valueOf(1))
                .setExpiration(new Date((new Date()).getTime() - 1))    // 8
                .compact();

        assertThatExceptionOfType(CustomAuthenticationException.class)
                .isThrownBy(() -> jwtTokenProvider.validateToken(expiredToken));
    }

    @DisplayName("시크릿 키가 틀린 토큰 정보로 payload를 조회할 경우 예외를 발생시킨다.")
    @Test
    void getPayloadByWrongSecretKeyToken() {
        final String invalidSecretToken = invalidSecretKeyJwtTokenProvider.createToken(String.valueOf(1));    // 9
        assertThatExceptionOfType(CustomAuthenticationException.class)
                .isThrownBy(() -> jwtTokenProvider.validateToken(invalidSecretToken));
    }
}
