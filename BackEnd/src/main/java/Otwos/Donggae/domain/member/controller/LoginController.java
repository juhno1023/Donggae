package Otwos.Donggae.domain.member.controller;

import Otwos.Donggae.DTO.member.login.GitHubUserInfo;
import Otwos.Donggae.DTO.member.login.GithubToken;
import Otwos.Donggae.DTO.member.login.LoginResponse;
import Otwos.Donggae.Jwt.TokenProvider;
import Otwos.Donggae.domain.member.service.LoginService;
import Otwos.Donggae.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;
    private final TokenProvider tokenProvider;
    private final MemberService memberService;

     // Authorization callback URL을 통해 임시 code 발급
     // 이를 이용해 AccessToken 발급 받기
     // AccessToken을 이용해 user 정보 가져오기
    @GetMapping("/github/callback")
    public ResponseEntity<?> githubLogin(@RequestParam String code) {
        try{
            log.info("github/callback");
            GithubToken githubToken = loginService.getAccessToken(code); //AccessToken 발급 받기

            GitHubUserInfo gitHubUserInfo = loginService.getGitHubUserInfo(githubToken);  //user 정보 받아오기
            log.info("githubUserName: {}", gitHubUserInfo.getUsername());

            Integer userId =  memberService.checkUserSignUp(gitHubUserInfo.getUsername()); // 깃허브 username을 통해 userId 가져오기
            if(userId == null){ // 회원가입 했는 지 검사
                log.info("Signup First");
                return ResponseEntity.notFound().build(); // 회원 가입 안 했음을 반환
            }

            String token = tokenProvider.createToken(String.valueOf(userId)); //userId 이용해 Jwt token 발급
            String userName = gitHubUserInfo.getUsername(); // 깃허브 네임
            String profile = gitHubUserInfo.getProfileUrl(); // 프로필 url
            String userEmail = memberService.getUserEmail(userId);// 이메일

            // github_stats 저장
            loginService.getUserRepositories(userName, githubToken, gitHubUserInfo);

            LoginResponse loginResponse = new LoginResponse(token, userName, profile, userEmail); //response 생성
            log.info("token = {}", token);

            return ResponseEntity.ok().body(loginResponse);
        } catch (Exception e) {
            log.info("exception");
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}