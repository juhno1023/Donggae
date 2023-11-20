package Otwos.Donggae.DTO.RecruitPost.search;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchResponse {
    private List<LectureRecruitPost> lectureRecruitPosts;
    private List<NaturalRecruitPost> naturalRecruitPosts;
}
