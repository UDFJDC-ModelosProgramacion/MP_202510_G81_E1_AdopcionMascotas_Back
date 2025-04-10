package co.edu.udistrital.mdp.adopcion.entities.adoption;

import java.util.ArrayList;
import java.util.List;

import co.edu.udistrital.mdp.adopcion.entities.events.EventEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;
@Data
@MappedSuperclass
public class AdoptionEntity extends EventEntity{
    @PodamExclude
    @OneToMany(mappedBy="adoption")
	private List<OwnerEntity> owners = new ArrayList<>();

    @PodamExclude
    @OneToOne
    private VeterinarianEntity veterinarian;

    @PodamExclude
    @OneToOne
    private PetEntity pet;

    private String description;
    private String observations;
    
}
