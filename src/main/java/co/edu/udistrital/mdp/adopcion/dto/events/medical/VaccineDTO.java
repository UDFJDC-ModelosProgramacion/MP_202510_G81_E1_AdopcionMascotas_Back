package co.edu.udistrital.mdp.adopcion.dto.events.medical;

import java.sql.Date;

import lombok.Data;

@Data
public class VaccineDTO extends MedicalEventDTO {
    private Long id;
    private String name;
    private String brandName;
    private Date nextDate;
    private Double dosis;
    private VaccineCardDTO vaccineCard;
}
