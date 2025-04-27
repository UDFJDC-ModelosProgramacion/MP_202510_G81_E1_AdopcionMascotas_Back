package co.edu.udistrital.mdp.adopcion.entities.events;

import co.edu.udistrital.mdp.adopcion.entities.ShelterEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetConditionEnum;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class ShelterArrivalEntity extends EventEntity{
    private String arrivalDetails;
    private Date arrivalDate;
    private PetConditionEnum petCondition;
    
    @PodamExclude
    @ManyToOne
    private ShelterEntity shelter;
    @ManyToOne
    private PetEntity pet;
}