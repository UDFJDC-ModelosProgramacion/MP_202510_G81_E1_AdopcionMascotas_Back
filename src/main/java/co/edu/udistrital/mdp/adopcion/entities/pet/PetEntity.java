package co.edu.udistrital.mdp.adopcion.entities.pet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import co.edu.udistrital.mdp.adopcion.entities.BaseEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionApplicationEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.EventEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.medical.VaccineCardEntity;
import co.edu.udistrital.mdp.adopcion.entities.multimedia.MultimediaEntity;
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

    @PodamExclude
    @OneToMany(mappedBy="pet")
    private List<OwnerEntity> owners = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "pet")
    private List<AdoptionApplicationEntity> adoptionAplications = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "pet")
    private List<EventEntity> events = new ArrayList<>();

    @PodamExclude
    @OneToOne(mappedBy = "pet")
    private MultimediaEntity multimedia;

    @PodamExclude
    @OneToOne(mappedBy = "pet")
    private AdoptionEntity adoption;

    @PodamExclude
    @OneToOne
    private VaccineCardEntity vaccineCard;
}
