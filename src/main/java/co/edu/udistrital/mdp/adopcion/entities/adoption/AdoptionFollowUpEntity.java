package co.edu.udistrital.mdp.adopcion.entities.adoption;

import co.edu.udistrital.mdp.adopcion.entities.BaseEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class AdoptionFollowUpEntity extends BaseEntity{
    String description;
    String observations;
    FollowUpStatusEnum followUpStatus;

    @PodamExclude
    @ManyToOne
    private VeterinarianEntity veterinarian;
    
}
