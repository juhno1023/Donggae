package Otwos.Donggae.Jwt;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor  {
    //토큰을 추출해 현재 사용자가 로그인한 상태인지를 체크하는 처리
    private final TokenProvider tokenProvider;

    public AuthInterceptor(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 컨트롤러가 실행되기 전에 호출되는 콜백 메서드
        if (isPreflight(request)) {
            return true;
        }

        String token = extractToken(request); // request에서 토큰 추출
        log.info("token = {}", token);

        tokenProvider.validateToken(token); // 유효한 토큰인지
        
        return true;
    }

    private boolean isPreflight(final HttpServletRequest request) {
        // HTTP 요청이 OPTIONS 메서드를 사용하는지 확인
        // CORS의 사전 요청(실제 요청 전에 브라우저가 서버에 보내는 요청)인지를 판단
        return request.getMethod().equals(HttpMethod.OPTIONS.toString());
    }

    private String extractToken(HttpServletRequest request) {
        try {
            return AuthorizationExtractor.extractOrThrow(request);
        } catch (IllegalArgumentException e) {
            throw new CustomAuthenticationException("extractToken fail");
        }
    }
}
