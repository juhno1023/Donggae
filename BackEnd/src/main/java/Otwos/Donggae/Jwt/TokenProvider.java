package Otwos.Donggae.Jwt;

public interface TokenProvider {
    String createToken(String payload);

    String parsePayload(String token);

    void validateToken(String token);
}
