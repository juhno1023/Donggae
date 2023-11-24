package Otwos.Donggae.domain.test.controller;

import Otwos.Donggae.DTO.test.TestQuestionDTO;
import Otwos.Donggae.DTO.test.showTestFields.TestDTO;
import Otwos.Donggae.Jwt.Auth;
import Otwos.Donggae.domain.test.service.TestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/test/{testId}")
    public ResponseEntity<?> showTestQuestions(@PathVariable("testId") int testId){
        try{
            List<TestQuestionDTO> testQuestionDTOList = testService.showTestQuestions(testId);
            return ResponseEntity.ok().body(testQuestionDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/test/save")
    public ResponseEntity<?> saveTestAnswer(@Auth int userId){
        try{

            return ResponseEntity.ok().body("dd");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
