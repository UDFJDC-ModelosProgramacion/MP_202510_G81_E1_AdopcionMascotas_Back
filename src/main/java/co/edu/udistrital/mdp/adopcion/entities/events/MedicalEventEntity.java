package co.edu.udistrital.mdp.adopcion.entities.events;

import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@MappedSuperclass
public class MedicalEventEntity extends EventEntity {
    @PodamExclude
    @ManyToOne
    VeterinarianEntity veterinarian;
}
