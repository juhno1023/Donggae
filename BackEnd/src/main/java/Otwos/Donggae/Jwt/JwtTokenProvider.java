package Otwos.Donggae.Jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider implements TokenProvider{

    private final SecretKey key;
    private final long validityInMilliseconds; // 토큰 유효시간

    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.validityInMilliseconds = 3600000 * 2; // 2 시간
    }

    @Override
    public String createToken(String payload) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(now) // 현재 시간 기반으로 생성
                .setExpiration(validity) // 만료 시간 세팅
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String parsePayload(String token) {
        // 토큰에서 정보 추출
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            log.error("parsePayload: error in token: " + token);
            throw new CustomAuthenticationException("parsePayload fail");
        }
    }

    @Override
    public void validateToken(String token) {
        // Jwt 토큰 유효성 검사
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            log.error("expired token: " + token);
            throw new CustomAuthenticationException("validateToken expired token");
        } catch (JwtException | IllegalArgumentException e) {
            log.error("invalid token: " + token);
            throw new CustomAuthenticationException("validateToken invalid token");
        }
    }
}
