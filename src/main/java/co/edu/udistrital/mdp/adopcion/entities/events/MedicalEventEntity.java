package co.edu.udistrital.mdp.adopcion.entities.events;

import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class MedicalEventEntity extends EventEntity {
    VeterinarianEntity veterinarian;
}
