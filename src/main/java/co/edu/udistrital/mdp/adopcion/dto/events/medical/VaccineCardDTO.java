package co.edu.udistrital.mdp.adopcion.dto.events.medical;

import java.util.Date;

import lombok.Data;


@Data
public class VaccineCardDTO extends MedicalEventDTO {
    private Long id;
    private Date lastVacineDate;
    private Date lastDewormingDate;
    private PetDTO pet;
}
