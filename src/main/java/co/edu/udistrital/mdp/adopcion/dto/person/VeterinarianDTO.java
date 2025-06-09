package co.edu.udistrital.mdp.adopcion.dto.person;

import co.edu.udistrital.mdp.adopcion.entities.person.Speciality;
import lombok.Data;

@Data
public class VeterinarianDTO {
    private Long id;
    private String licenseNumber;
    private Speciality speciality;
    
}