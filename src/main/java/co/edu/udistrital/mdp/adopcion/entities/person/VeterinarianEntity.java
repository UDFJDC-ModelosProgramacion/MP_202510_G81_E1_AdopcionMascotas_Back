package co.edu.udistrital.mdp.adopcion.entities.person;

import co.edu.udistrital.mdp.adopcion.entities.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;
@Data
@Entity
public class VeterinarianEntity extends PersonEntity {
    private String licenseNumber;
    private String specialty;



}
