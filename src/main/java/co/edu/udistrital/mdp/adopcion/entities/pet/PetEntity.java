package co.edu.udistrital.mdp.adopcion.entities.pet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.yaml.snakeyaml.events.Event;

import co.edu.udistrital.mdp.adopcion.entities.BaseEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionAplicationEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.EventEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.medical.VaccineCardEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;


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

@PodamExclude
@OneToMany(mappedBy="pet")
    private List<OwnerEntity> owners = new ArrayList<>();

@PodamExclude
@OneToMany(mappedBy = "pet")
    private List<AdoptionAplicationEntity> adoptionAplications = new ArrayList<>();

@PodamExclude 
@OneToMany(mappedBy = "pet")
    private List<EventEntity> events = new ArrayList<>();

@PodamExclude
@OneToOne(mappedBy = "pet")
    private Mult;
}
