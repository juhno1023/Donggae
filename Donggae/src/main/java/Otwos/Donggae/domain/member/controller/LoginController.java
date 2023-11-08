package Otwos.Donggae.domain.member.controller;

import Otwos.Donggae.DTO.member.login.GitHubUserInfo;
import Otwos.Donggae.DTO.member.login.GithubToken;
import Otwos.Donggae.domain.member.service.LoginServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@Slf4j
public class LoginController {

    @Value("${github.client-id}")
    private String clientId;

    @Value("${github.client-secret}")
    private String clientSecret;

    @Value("${github.redirect-url}")
    private String redirectURI;

    private final LoginServiceImpl loginServiceImpl;

    public LoginController(LoginServiceImpl loginServiceImpl) {
        this.loginServiceImpl = loginServiceImpl;
    }


    @GetMapping("/github/login")
    public void redirectGithub(HttpServletResponse response) throws IOException {
        String redirect_uri = "https://github.com/login/oauth/authorize?client_id="+clientId+"&redirect_uri="+redirectURI;
        response.sendRedirect(redirect_uri);
    }


     // Authorization callback URL을 통해 임시 code 발급
     // 이를 이용해 AccessToken 발급 받기
     // AccessToken을 이용해 user 정보 가져오기
    @GetMapping("/auth/github/callback")
    public ResponseEntity<String> githubLogin(@RequestParam String code, HttpServletResponse response) {
        GithubToken githubToken = loginServiceImpl.getAccessToken(code); //AccessToken 발급 받기

        GitHubUserInfo gitHubUserInfo = loginServiceImpl.getGitHubUserInfo(githubToken);  //user 정보 받아오기
        log.info("githubUserName: {}", gitHubUserInfo.getUsername());
        log.info("githubUserProfile: {}", gitHubUserInfo.getProfileUrl());
        log.info("githubUserIdNum: {}", gitHubUserInfo.getIdNumber());
        response.setHeader("Authorization", githubToken.getAuthorizationValue());
        return ResponseEntity.ok("logined");
    }
}