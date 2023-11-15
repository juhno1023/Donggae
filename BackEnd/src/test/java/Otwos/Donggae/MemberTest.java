package Otwos.Donggae;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserInterestField;
import Otwos.Donggae.domain.member.repository.MemberRepository;
import Otwos.Donggae.domain.member.repository.info.UserInterestFieldRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberTest {

    @Autowired
    private UserInterestFieldRepository userInterestFieldRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    void ttt(){
        User user = memberRepository.findUserByUserId(1); // userId가 Long 타입이라면 여기도 Long 타입을 사용
        List<UserInterestField> interestFields = userInterestFieldRepository.findUserInterestFieldsByUserId(user);
        assertThat(interestFields).isNotEmpty(); // 결과 리스트가 비어 있지 않은지 확인
        assertThat(interestFields.get(0).getInterestField().name()).isEqualTo("모집분야"); // 첫 번째 항목의 interestField와 비교

    }
}
