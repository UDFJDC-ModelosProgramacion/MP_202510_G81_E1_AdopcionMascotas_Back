package co.edu.udistrital.mdp.adopcion.entities.adoption;


import co.edu.udistrital.mdp.adopcion.entities.events.EventEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;
@Data
@MappedSuperclass
public class AdoptionEntity extends EventEntity{
    @PodamExclude
    @ManyToOne
	private  OwnerEntity owner;

    @PodamExclude
    @OneToOne
    private VeterinarianEntity veterinarian;

    @PodamExclude
    @OneToOne
    private PetEntity pet;

    @PodamExclude
    @OneToOne(mappedBy = "adoption")
    private AdoptionApplicationEntity adoptionApplication;

    private String description;
    private String observations;
    private AdoptionStatusEnum type;
    
}
