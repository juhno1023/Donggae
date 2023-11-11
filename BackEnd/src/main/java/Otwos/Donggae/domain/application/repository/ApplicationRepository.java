package Otwos.Donggae.domain.application.repository;

import Otwos.Donggae.DAO.Application;
import Otwos.Donggae.DAO.Recruit.RecruitPost;
import Otwos.Donggae.DAO.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Application findApplicationByUserIdAndRecruitPostId(User user, RecruitPost recruitPost);
}
