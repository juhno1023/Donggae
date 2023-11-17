package Otwos.Donggae.domain.RecruitPost.service;

import Otwos.Donggae.DAO.User.UserInterestField;
import Otwos.Donggae.DTO.RecruitPost.RecRecruitPostDTO;

import java.util.List;


public interface RecRecruitPostService {
    List<UserInterestField> recommendRecruitPost(int userId);
}
