package co.edu.udistrital.mdp.adopcion.entities.events.medical;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.edu.udistrital.mdp.adopcion.entities.events.MedicalEventEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class VaccineCardEntity extends MedicalEventEntity {
    private Date LastVacineDate;
    private Date LastDewormingDate;

    @PodamExclude
    @OneToMany(mappedBy="vaccineCard")
	private List<VaccineEntity> vaccines = new ArrayList<>();

    @OneToMany(mappedBy="vaccineCard")
	private List<DewormingEntity> dewormings = new ArrayList<>();

    @OneToOne(mappedBy="vaccineCard")
    private PetEntity pet;
}
