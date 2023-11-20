package Otwos.Donggae;

import Otwos.Donggae.DAO.User.User;
import Otwos.Donggae.DAO.User.UserInterestField;
import Otwos.Donggae.Global.LanguageEnum;
import Otwos.Donggae.domain.RecruitPost.Repository.RecruitPostRepository;
import Otwos.Donggae.domain.RecruitPost.Repository.info.RecruitLanguageRepository;
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

    @Autowired
    private RecruitLanguageRepository recruitLanguageRepository;

    @Autowired
    private RecruitPostRepository recruitPostRepository;

    @Test
    @Transactional
    void userIdFKTest(){
        User user = memberRepository.findUserByUserId(1);
        List<UserInterestField> interestFields = userInterestFieldRepository.findAllByUserId(user);
        assertThat(interestFields).isNotEmpty(); // 결과 리스트가 비어 있지 않은지 확인
        assertThat(interestFields.get(0).getInterestField().name()).isEqualTo("모집분야"); // 첫 번째 항목의 interestField와 비교

        recruitPostRepository.findAllByIsCompleteAndUserIdNot(false, user);

    }

    @Test
    @Transactional
    void enumTest(){
        recruitLanguageRepository.findAllByLanguage(LanguageEnum.valueOf("C플라스플라스"));

    }
}
