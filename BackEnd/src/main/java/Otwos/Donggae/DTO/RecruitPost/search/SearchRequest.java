package Otwos.Donggae.DTO.RecruitPost.search;

import Otwos.Donggae.DTO.member.userinfo.response.UserInterestFieldResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserLanguageResponse;
import Otwos.Donggae.DTO.member.userinfo.response.UserPersonalityResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    List<UserLanguageResponse> languageS;
    List<UserInterestFieldResponse> fieldS;
    List<UserPersonalityResponse> personalityS;
    private String majorLecture;
}
