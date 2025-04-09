package co.edu.udistrital.mdp.adopcion.entities.adoption;


import co.edu.udistrital.mdp.adopcion.entities.BaseEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity

public class AdoptionAplicationEntity {
    private Long id;
    private PetEntity pet;
    private OwnerEntity owner;
    private VeterinarianEntity veterinarian;
    private String status;
    private String description;

}
