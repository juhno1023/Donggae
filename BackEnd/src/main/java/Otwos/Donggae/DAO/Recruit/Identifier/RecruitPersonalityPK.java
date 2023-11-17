package Otwos.Donggae.DAO.Recruit.Identifier;

import java.io.Serializable;

import Otwos.Donggae.Global.PersonalityEnum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RecruitPersonalityPK implements Serializable {
    private int recruitPostId;
    private PersonalityEnum personality;
}
