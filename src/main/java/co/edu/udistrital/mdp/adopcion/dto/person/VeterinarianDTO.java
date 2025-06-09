package co.edu.udistrital.mdp.adopcion.dto.person;

import co.edu.udistrital.mdp.adopcion.entities.person.Speciality;
import lombok.Data;

@Data
public class VeterinarianDTO extends PersonDTO {

    private Long id;

    private String licenseNumber;
    private Speciality speciality;
    
}