package Otwos.Donggae.Jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthResolver implements HandlerMethodArgumentResolver {
//특정 조건을 충족하는 메서드 파라미터의 값을 동적으로 해석
    private final TokenProvider tokenProvider;

    public AuthResolver(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Auth.class); // Auth 어노테이션이 파라미터에 붙어있다면, 리졸버가 해당 파라미터를 지원
    }

    @Override
    public Integer resolveArgument(MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) {
        // 컨트롤러 메서드의 인자 값을 해석
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        String token = extractToken(request); //요청으로부터 토큰을 추출
        String payload = tokenProvider.parsePayload(token); // 토큰에서 정보(페이로드)를 파싱

        return Integer.parseInt(payload); // userId가 int형
    }

    private String extractToken(HttpServletRequest request) {
        try {
            return AuthorizationExtractor.extractOrThrow(request);
        } catch (IllegalArgumentException e) {
            throw new CustomAuthenticationException("extractToken fail");
        }
    }
}
