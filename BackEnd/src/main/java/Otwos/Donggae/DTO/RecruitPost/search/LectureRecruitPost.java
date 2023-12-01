package Otwos.Donggae.DTO.RecruitPost.search;

import Otwos.Donggae.DTO.member.userinfo.response.UserLanguageResponse;
import Otwos.Donggae.DTO.team.teamDetail.ApplyMemberPreview;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LectureRecruitPost {
    private int postId; //id
    private String title; //제목
    private List<UserLanguageResponse> languageS; //모집 언어들
    private ApplyMemberPreview teamLeader; //팀장
    private String createdDate; //작성날짜
    private String lecture; //해당 강의
}
