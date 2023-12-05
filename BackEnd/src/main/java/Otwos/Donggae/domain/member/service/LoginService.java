package Otwos.Donggae.domain.member.service;

import Otwos.Donggae.DTO.member.login.GitHubUserInfo;
import Otwos.Donggae.DTO.member.login.GithubToken;

public interface LoginService {
    
    // access token 받아 오기
    public GithubToken getAccessToken(String code);
    
    // user 정보 받아 오기
    public GitHubUserInfo getGitHubUserInfo(GithubToken githubToken);

    // 랭킹에 활용할 정보 가져오기
    public void getUserRepositories(String username, GithubToken githubToken, GitHubUserInfo gitHubUserInfo);
}
