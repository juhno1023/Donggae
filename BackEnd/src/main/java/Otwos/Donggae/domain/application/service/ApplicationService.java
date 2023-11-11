package Otwos.Donggae.domain.application.service;

import Otwos.Donggae.DTO.application.ApplyDTO;
import Otwos.Donggae.DTO.application.read.ReadApplicationRequest;
import Otwos.Donggae.DTO.application.read.ReadApplicationResponse;
import Otwos.Donggae.DTO.member.previewInfo.PreviewUserInfoDTO;

public interface ApplicationService {
    public void applyFor(int userId, ApplyDTO applyDTO);
    public ReadApplicationResponse readApplication(int userId, ReadApplicationRequest applicationRequest);
    public PreviewUserInfoDTO applyPageInfo(int userId);
}
