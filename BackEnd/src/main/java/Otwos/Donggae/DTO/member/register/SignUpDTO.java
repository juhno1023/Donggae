package Otwos.Donggae.DTO.member.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SignUpDTO {
    private String githubName;
    private String dguEmail;
}
