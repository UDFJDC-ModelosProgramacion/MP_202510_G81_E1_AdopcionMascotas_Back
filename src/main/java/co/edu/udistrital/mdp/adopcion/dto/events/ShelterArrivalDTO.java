package co.edu.udistrital.mdp.adopcion.dto.events;

import co.edu.udistrital.mdp.adopcion.entities.pet.PetConditionEnum;
import lombok.Data;

@Data
public class ShelterArrivalDTO extends EventDTO {
    private PetDTO pet;
    private ShelterDTO shelter;
    private VeterinarianDTO veterinarian;
    private PetConditionEnum petCondition;
}
