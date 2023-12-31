package Otwos.Donggae.Jwt;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Enumeration;

public class AuthorizationExtractor {
    public static final String AUTHORIZATION = "Authorization";
    public static String BEARER_TYPE = "Bearer";
    public static final String ACCESS_TOKEN_TYPE = AuthorizationExtractor.class.getSimpleName() + ".ACCESS_TOKEN_TYPE";

    /*
         Input : Authorization: Bearer <토큰값>
         Output : <토큰값>
    */
    public static String extractOrThrow(final HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders(AUTHORIZATION); // header에서 JWT를 받아온다
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if ((value.toLowerCase().startsWith(BEARER_TYPE.toLowerCase()))) {
                String authHeaderValue = value.substring(BEARER_TYPE.length()).trim();
                request.setAttribute(ACCESS_TOKEN_TYPE, value.substring(0, BEARER_TYPE.length()).trim());
                int commaIndex = authHeaderValue.indexOf(',');
                if (commaIndex > 0) {
                    authHeaderValue = authHeaderValue.substring(0, commaIndex);
                }
                return authHeaderValue;
            }
        }
        throw new IllegalArgumentException();
    }

}
