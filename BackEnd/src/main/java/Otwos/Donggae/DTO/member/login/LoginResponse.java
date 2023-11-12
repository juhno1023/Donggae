package Otwos.Donggae.DTO.member.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String username; // 깃허브 고유 아이디 이름
    private String profileUrl; // 깃허브 프로필 이미지 url
    private String userEmail;
}
