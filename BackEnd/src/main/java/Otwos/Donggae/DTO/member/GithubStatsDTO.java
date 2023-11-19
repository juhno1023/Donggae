package Otwos.Donggae.DTO.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GithubStatsDTO {
    private Integer totalIssues;
    private Integer totalCommits;
    private Integer totalPRs;
    private Integer totalStars;
}
