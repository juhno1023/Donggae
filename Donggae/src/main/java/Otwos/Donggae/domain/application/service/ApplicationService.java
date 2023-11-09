package Otwos.Donggae.domain.application.service;

import Otwos.Donggae.DTO.application.ApplyDTO;
import Otwos.Donggae.DTO.application.ApplyPage.ApplyPageRequest;
import Otwos.Donggae.DTO.application.read.ReadApplicationRequest;
import Otwos.Donggae.DTO.application.read.ReadApplicationResponse;
import Otwos.Donggae.DTO.member.previewInfo.PreviewUserInfoDTO;

public interface ApplicationService {
    public void applyFor(ApplyDTO applyDTO);
    public ReadApplicationResponse readApplication(ReadApplicationRequest applicationRequest);
    public PreviewUserInfoDTO applyPageInfo(ApplyPageRequest applyPageRequest);
}
