package Otwos.Donggae.domain.test.controller;

import Otwos.Donggae.DTO.test.showTestFields.TestDTO;
import Otwos.Donggae.domain.test.service.TestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/testpage")
    public ResponseEntity<?> showTestFields() {
        try {
            List<TestDTO> testDTOS = testService.showTestFields();
            return ResponseEntity.ok().body(testDTOS);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
