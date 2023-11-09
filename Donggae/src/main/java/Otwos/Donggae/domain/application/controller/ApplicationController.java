package Otwos.Donggae.domain.application.controller;

import Otwos.Donggae.DTO.application.ApplyDTO;
import Otwos.Donggae.DTO.application.read.ReadApplicationRequest;
import Otwos.Donggae.DTO.application.read.ReadApplicationResponse;
import Otwos.Donggae.domain.application.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<?> applyForTeam(@RequestBody ApplyDTO applyDTO) {
        try {
            applicationService.applyFor(applyDTO);
            return ResponseEntity.ok("지원 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/apply/show")
    public ResponseEntity<?> showApplication(@RequestBody ReadApplicationRequest request) {
        try {
            ReadApplicationResponse response = applicationService.readApplication(request);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
