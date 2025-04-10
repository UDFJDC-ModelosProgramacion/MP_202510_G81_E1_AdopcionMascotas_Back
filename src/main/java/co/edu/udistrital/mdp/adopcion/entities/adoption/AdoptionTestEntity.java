package co.edu.udistrital.mdp.adopcion.entities.adoption;

import java.sql.Date;

import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@MappedSuperclass
public class AdoptionTestEntity extends AdoptionEntity {
    private String description;
    private Date testEnd;
    private String testObservations;
    private TestResultEnum typeTest;

    @PodamExclude
    @ManyToOne
	private OwnerEntity owner;

    @ManyToOne
	private VeterinarianEntity veterinarian;
    
}
