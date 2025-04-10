package co.edu.udistrital.mdp.adopcion.entities.events.medical;
import co.edu.udistrital.mdp.adopcion.entities.events.MedicalEventEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class DewormingEntity extends MedicalEventEntity{
    @PodamExclude
    @ManyToOne
	private VaccineCardEntity vaccineCard;

    private String brandName;
    private Date nextDate;
    private Double dosis;
    private VeterinarianEntity veterinarian;
    private DewormingTypeEnum type;

    

}
