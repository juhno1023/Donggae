package Otwos.Donggae.domain.team.controller;

import Otwos.Donggae.DTO.team.selectTeamMember.SelectTeamMemberRequest;
import Otwos.Donggae.domain.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping("/team/selection")
    public ResponseEntity<?> selectTeamMember(@RequestBody SelectTeamMemberRequest request) {
        try {
            teamService.selectTeamMember(request);
            return ResponseEntity.ok("팀원 선택 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/team/deletion")
    public ResponseEntity<?> deleteTeamMember(@RequestBody SelectTeamMemberRequest request) {
        try {
            teamService.deleteTeamMember(request);
            return ResponseEntity.ok("팀원 추방 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
