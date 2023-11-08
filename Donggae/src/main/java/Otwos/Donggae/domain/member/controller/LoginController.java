package Otwos.Donggae.domain.member.controller;

import Otwos.Donggae.DTO.member.login.GitHubUserInfo;
import Otwos.Donggae.DTO.member.login.GithubToken;
import Otwos.Donggae.DTO.member.login.LoginResponse;
import Otwos.Donggae.Jwt.TokenProvider;
import Otwos.Donggae.domain.member.service.LoginServiceImpl;
import Otwos.Donggae.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    @Value("${github.client-id}")
    private String clientId;

    @Value("${github.client-secret}")
    private String clientSecret;

    @Value("${github.redirect-url}")
    private String redirectURI;

    private final LoginServiceImpl loginServiceImpl;
    private final TokenProvider tokenProvider;

    @Autowired
    private MemberService memberService;


    @GetMapping("/github/login")
    public void redirectGithub(HttpServletResponse response) throws IOException {
        String redirect_uri = "https://github.com/login/oauth/authorize?client_id="+clientId+"&redirect_uri="+redirectURI;
        response.sendRedirect(redirect_uri);
    }


     // Authorization callback URL을 통해 임시 code 발급
     // 이를 이용해 AccessToken 발급 받기
     // AccessToken을 이용해 user 정보 가져오기
    @GetMapping("/github/callback")
    public ResponseEntity<?> githubLogin(@RequestParam String code, HttpServletResponse response) {
        try{
            GithubToken githubToken = loginServiceImpl.getAccessToken(code); //AccessToken 발급 받기

            GitHubUserInfo gitHubUserInfo = loginServiceImpl.getGitHubUserInfo(githubToken);  //user 정보 받아오기
            log.info("githubUserName: {}", gitHubUserInfo.getUsername());
            log.info("githubUserProfile: {}", gitHubUserInfo.getProfileUrl());
            log.info("githubUserIdNum: {}", gitHubUserInfo.getIdNumber());

            if(memberService.checkUserSignUp(gitHubUserInfo.getUsername()) == null){ // 회원가입 했는 지 검사
                log.info("Signup First");
                return ResponseEntity.notFound().build(); // 회원 가입 안 했음을 반환
            }

            String token = tokenProvider.createToken(String.valueOf(gitHubUserInfo.getIdNumber())); //Jwt token 발급
            LoginResponse loginResponse = new LoginResponse(token); //response 생성
            response.setHeader("Authorization", githubToken.getAuthorizationValue());
            return ResponseEntity.ok().body(loginResponse);
        } catch (Exception e) {
            log.info("exception");
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}