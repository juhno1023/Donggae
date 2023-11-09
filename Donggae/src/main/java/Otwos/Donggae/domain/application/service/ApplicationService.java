package Otwos.Donggae.domain.application.service;

import Otwos.Donggae.DTO.application.ApplyDTO;
import Otwos.Donggae.DTO.application.read.ReadApplicationRequest;
import Otwos.Donggae.DTO.application.read.ReadApplicationResponse;

public interface ApplicationService {
    public void applyFor(ApplyDTO applyDTO);
    public ReadApplicationResponse readApplication(ReadApplicationRequest applicationRequest);
}
