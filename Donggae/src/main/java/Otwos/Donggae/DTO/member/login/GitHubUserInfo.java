package Otwos.Donggae.DTO.member.login;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GitHubUserInfo {

    private final String idNumber; // 깃허브 id 넘버
    private final String username; // 깃허브 고유 아이디 이름
    private final String profileUrl; // 깃허브 프로필 이미지 url
//    private final Integer commitNum; // 커밋 수
//    private final Integer issueNum; // 이슈 수
//    private final Integer starNum; // 스타 수
//    private final Integer prNum; // pull request 수

    @JsonCreator
    public GitHubUserInfo(
            @JsonProperty("login") String username,
            @JsonProperty("id") String idNumber,
            @JsonProperty("avatar_url") String profileUrl) {

        this.username = username;
        this.idNumber = idNumber;
        this.profileUrl = profileUrl;
    }

}
