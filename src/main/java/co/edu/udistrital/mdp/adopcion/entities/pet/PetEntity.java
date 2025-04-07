package co.edu.udistrital.mdp.adopcion.entities.pet;

import java.util.Date;

import co.edu.udistrital.mdp.adopcion.entities.BaseEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.medical.VaccineCardEntity;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity

public class PetEntity extends BaseEntity {

private String name;
private Date birthDate;
private String breed;
private SizeEnum size;
private GenderEnum gender;
private OwnerEntity owner;
private String behaviorProfile;
private VaccineCardEntity vaccineCard;

}
