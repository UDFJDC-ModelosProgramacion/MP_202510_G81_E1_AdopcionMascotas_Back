package co.edu.udistrital.mdp.adopcion.entities.adoption;


import java.util.Date;

import co.edu.udistrital.mdp.adopcion.entities.events.EventEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@MappedSuperclass

public class AdoptionApplicationEntity extends EventEntity{
    @PodamExclude
    @ManyToOne
	private OwnerEntity owner;

    @PodamExclude
    @ManyToOne
	private VeterinarianEntity veterinarian;

    @PodamExclude
    @OneToOne
    private AdoptionEntity adoption;

    Date applicationDate;
    Date applicationEnd;
    String observations;
    ApplicationStatusEnum typeApplicationStatus;
    ApplicationResultEnum typeResult;



}
