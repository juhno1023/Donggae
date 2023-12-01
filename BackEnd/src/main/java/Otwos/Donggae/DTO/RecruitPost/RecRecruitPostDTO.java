package Otwos.Donggae.DTO.RecruitPost;


import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class RecRecruitPostDTO {

    private int recruitPostId;

    private String majorLectureName;

    private String title;

    private Set<String> recruitLanguages;

    private String donggaeRank;

    private String bojRank;

    private String userName;

    private String createDate;
}
