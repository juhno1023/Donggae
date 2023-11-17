package Otwos.Donggae.DTO.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyTeamRequest {
    private String selfIntro;
    private String content;
    private int recruitPostId;
}
