package Otwos.Donggae.Jwt;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationException extends RuntimeException {
    public CustomAuthenticationException(String message) {
        super(message);
        log.info(message);
    }
}
