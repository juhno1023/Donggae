package Otwos.Donggae.domain.member.service;
import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DTO.member.baekjoonRank.UserBaekjoonRankRequestDTO;
import Otwos.Donggae.Global.Rank.BaekjoonRank;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class SolvedacServiceImpl implements SolvedacService {

    private String SOLVEDAC_API_URL = "https://solved.ac/api/v3/user/show?handle=";
    private final String[] allTier = {"Bronze", "Silver", "Gold", "Platinum", "Diamond", "Ruby"};
    private final String[] allSubtier = {"V", "IV", "III", "II", "I"};

    @Autowired
    private MemberRepository userRepository;

    @Override
    @Transactional
    public void getMyBaekjoonRank(UserBaekjoonRankRequestDTO userBaekjoonRankRequestDTO, int userId) {

        String userBaekjoonName = userBaekjoonRankRequestDTO.getBaekjoonUserName();
        SOLVEDAC_API_URL = SOLVEDAC_API_URL + userBaekjoonName;

        System.out.println(SOLVEDAC_API_URL);

        // Solved.ac에서 사용자 정보 가져오기
        int userTier = getTierFromSolvedac(SOLVEDAC_API_URL);

        // Solved.ac에서 가져온 정보를 기반으로 백준 랭크 계산
        String solvedacTier = calculateSolvedacTier(userTier);
        BaekjoonRank baekjoonRank = BaekjoonRank.valueOfLabel(solvedacTier.replace(" ", "_"));

        // 해당 유저의 백준 랭크 업데이트
        User user = userRepository.findUserByUserId(userId);
        SOLVEDAC_API_URL = "https://solved.ac/api/v3/user/show?handle=";

        if (user != null) {
            user.setBoj_rank(baekjoonRank);
            userRepository.save(user);
        } else {
            // 해당 userId로 사용자를 찾을 수 없는 경우에 대한 처리
            throw new RuntimeException("User not found with userId: " + userId);
        }
    }

    public int getTierFromSolvedac(String SOLVEDAC_API_URL) {
        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(SOLVEDAC_API_URL, String.class);

        try {
            // JSON 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            // "tier" 필드 추출
            int tier = jsonNode.get("tier").asInt();

            return tier;
        } catch (JsonProcessingException e) {
            // 예외 처리
            e.printStackTrace();
            return -1; // 또는 다른 적절한 값으로 처리
        }
    }

    public String calculateSolvedacTier(int idx) {
        int tier;
        int subtier;

        if (idx % 5 == 0) {
            tier = idx / 5 - 1;
        } else {
            tier = idx / 5;
        }

        if (idx % 5 == 1) {
            subtier = 0;
        } else if (idx % 5 == 0) {
            subtier = 4;
        } else {
            subtier = (idx % 5) - 1;
        }

        return String.format("%s %s", allTier[tier], allSubtier[subtier]);
    }
}
