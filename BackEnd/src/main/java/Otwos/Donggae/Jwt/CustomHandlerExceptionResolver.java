package Otwos.Donggae.Jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        if (ex instanceof CustomAuthenticationException) {
            try {
                String alertScript = "<script>alert('다시 로그인 해주세요');</script>";
                response.getWriter().write(alertScript);
                
                response.sendRedirect("http://localhost:3000/"); // 로그인 페이지로 리다이렉트
            } catch (IOException e) {
                e.printStackTrace(); // 로깅을 위한 예외 처리
            }
            return new ModelAndView(); // 추가 처리를 중단하고 빈 ModelAndView를 반환
        }
        return null; // 다른 예외 처리기에게 예외 처리를 위임
    }
}