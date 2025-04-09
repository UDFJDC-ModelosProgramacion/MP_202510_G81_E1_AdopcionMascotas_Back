package co.edu.udistrital.mdp.adopcion.entities.events.medical;
import java.util.Date;

import co.edu.udistrital.mdp.adopcion.entities.events.MedicalEventEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@MappedSuperclass
public abstract class VaccineCardEntity extends MedicalEventEntity {
    @PodamExclude
    private Date LastVacineDate;
    private Date LastDewormingDate;
}
