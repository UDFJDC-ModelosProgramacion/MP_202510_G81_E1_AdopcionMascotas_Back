package co.edu.udistrital.mdp.adopcion.entities.adoption;

import co.edu.udistrital.mdp.adopcion.entities.BaseEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import jakarta.persistence.Entity;
import lombok.Data;
@Data
@Entity
public class AdoptionEntity extends BaseEntity{
    private String description;
    private PetEntity pet;
    private OwnerEntity adopter;
    private VeterinarianEntity observer;
    private AdoptionStatusEnum adoptionStatus;
    private String observations;
}
