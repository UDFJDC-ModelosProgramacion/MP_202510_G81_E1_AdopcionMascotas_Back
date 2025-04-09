package co.edu.udistrital.mdp.adopcion.entities.events.medical;
import java.util.Date;

import co.edu.udistrital.mdp.adopcion.entities.BaseEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public abstract class VaccineEntity extends BaseEntity {
    @PodamExclude
    private String name;
    private String brandName;
    private Date nextDate;
    private VeterinarianEntity veterinarian;
    private Double dosis;
}
