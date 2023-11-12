package Otwos.Donggae.DTO.application.read;

import Otwos.Donggae.DTO.member.previewInfo.PreviewUserInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReadApplicationResponse {
    private PreviewUserInfoDTO previewUserInfoDTO;
    private String selfIntro;
    private String content;
}
