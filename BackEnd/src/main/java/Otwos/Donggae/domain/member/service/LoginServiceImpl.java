package Otwos.Donggae.domain.member.service;

import Otwos.Donggae.DAO.GithubStatus;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.member.GithubStatsDTO;
import Otwos.Donggae.DTO.member.login.GitHubUserInfo;
import Otwos.Donggae.DTO.member.login.GithubToken;
import Otwos.Donggae.domain.member.GraphQLQueryUtils;
import Otwos.Donggae.domain.member.repository.GithubStatusRepository;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    @Value("${github.client-id}")
    private String clientId;

    @Value("${github.client-secret}")
    private String clientSecret;

    @Value("${github.token-url}")
    private String url;

    @Value("${github.user-api-url}")
    private String userUrl;

    @Value("${github.graphql-url}")
    private String githubApiUrl;

    private final MemberRepository memberRepository;

    private final GithubStatusRepository githubStatusRepository;

    public GithubToken getAccessToken(String code) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/json"); //json 형식으로 응답 받음
        headers.setAll(header);

        MultiValueMap<String, String> requestPayloads = new LinkedMultiValueMap<>();
        Map<String, String> requestPayload = new HashMap<>();
        requestPayload.put("client_id", clientId);
        requestPayload.put("client_secret", clientSecret);
        requestPayload.put("code", code);
        requestPayloads.setAll(requestPayload);

        HttpEntity<?> request = new HttpEntity<>(requestPayloads, headers);
        ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, GithubToken.class); //미리 정의해둔 GithubToken 클래스 형태로 Response Body를 파싱해서 담아서 리턴
        return (GithubToken) response.getBody();
    }

    public GitHubUserInfo getGitHubUserInfo(GithubToken githubToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "token " + githubToken.getAccessToken());
            HttpEntity<Object> request = new HttpEntity<>(headers);

            // 사용자의 기본 정보 가져오기
            ResponseEntity<GitHubUserInfo> response = new RestTemplate().exchange(
                    userUrl, HttpMethod.GET, request, GitHubUserInfo.class
            );

            return response.getBody();
        } catch (Exception e) {
            log.error("Error during GitHub API call", e);
            throw e;
        }
    }

    @Override
    public void getUserRepositories(String username, GithubToken githubToken, GitHubUserInfo gitHubUserInfo) {
        Long githubId = gitHubUserInfo.getIdNumber();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer " + githubToken.getAccessToken());
        headers.set("Accept", "application/vnd.github.cloak-preview");

        String query = GraphQLQueryUtils.readGraphQLQuery("userInfoQuery.graphql");

        Map<String, String> requestPayload = Map.of("query", query);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestPayload, headers);
        ResponseEntity<Map<String, Object>> response = new RestTemplate().exchange(githubApiUrl, HttpMethod.POST, request, new ParameterizedTypeReference<>() {});

        Map<String, Object> responseData = response.getBody();

        GithubStatsDTO githubStats = getGithubStats(responseData);

        Integer totalIssues = githubStats.getTotalIssues();
        Integer totalCommits = githubStats.getTotalCommits();
        Integer totalPRs = githubStats.getTotalPRs();
        Integer totalStars = githubStats.getTotalStars();

        log.info("totalCommit = {}", totalCommits);
        log.info("totalPR = {}", totalPRs);
        log.info("totalIssue = {}", totalIssues);
        log.info("totalStars = {}", totalStars);

        User user = memberRepository.findUserByGithubName(username);
        user.setUserProfile(gitHubUserInfo.getProfileUrl());
        memberRepository.save(user);

        GithubStatus githubStatus = GithubStatus.builder()
                                                .githubId(githubId)
                                                .userId(user)
                                                .issueNum(totalIssues)
                                                .prNum(totalPRs)
                                                .starNum(totalStars)
                                                .commitNum(totalCommits)
                                                .build();
        githubStatusRepository.save(githubStatus);
    }

    private GithubStatsDTO getGithubStats(Map<String, Object> responseData) {
        GithubStatsDTO stats = new GithubStatsDTO(0, 0, 0, 0);

        if (responseData != null) {
            Map<String, Object> dataMap = (Map<String, Object>) responseData.get("data");
            if (dataMap != null) {
                Map<String, Object> viewerMap = (Map<String, Object>) dataMap.get("viewer");
                if (viewerMap != null) {
                    // 이슈, 커밋, PR, 스타 수를 추출하여 stats 객체에 설정
                    stats.setTotalIssues(extractTotalCount(viewerMap, "issues"));
                    stats.setTotalCommits(extractTotalCount(viewerMap, "contributionsCollection", "totalCommitContributions"));
                    stats.setTotalPRs(extractTotalCount(viewerMap, "pullRequests"));
                    stats.setTotalStars(extractStarsCount(viewerMap));
                }
            }
        }

        return stats;
    }

    private Integer extractTotalCount(Map<String, Object> map, String key) {
        Map<String, Object> nestedMap = (Map<String, Object>) map.get(key);
        return nestedMap != null ? (Integer) nestedMap.get("totalCount") : 0;
    }

    private Integer extractTotalCount(Map<String, Object> map, String key, String nestedKey) {
        Map<String, Object> nestedMap = (Map<String, Object>) map.get(key);
        return nestedMap != null ? (Integer) nestedMap.get(nestedKey) : 0;
    }

    private Integer extractStarsCount(Map<String, Object> viewerMap) {
        Map<String, Object> repositoriesMap = (Map<String, Object>) viewerMap.get("repositories");
        if (repositoriesMap != null) {
            List<Map<String, Object>> nodesList = (List<Map<String, Object>>) repositoriesMap.get("nodes");
            if (nodesList != null) {
                return nodesList.stream()
                        .map(node -> (Map<String, Object>) node.get("stargazers"))
                        .filter(Objects::nonNull)
                        .mapToInt(stargazers -> (Integer) stargazers.get("totalCount"))
                        .sum();
            }
        }
        return 0;
    }

}


