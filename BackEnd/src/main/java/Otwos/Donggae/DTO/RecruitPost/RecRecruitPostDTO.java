package Otwos.Donggae.DTO.RecruitPost;

import Otwos.Donggae.Global.Rank.BaekjoonRank;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecRecruitPostDTO {

    private int recruitPostId;

    private String majorLectureName;

    private String title;

    private List<String> recruitLanguages;

    private String donggaeRank;

    private BaekjoonRank bojRank;

    private String userName;

    private String createDate;



}
