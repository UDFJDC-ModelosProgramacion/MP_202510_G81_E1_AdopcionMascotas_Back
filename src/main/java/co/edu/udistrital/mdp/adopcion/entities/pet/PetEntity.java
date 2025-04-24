package co.edu.udistrital.mdp.adopcion.entities.pet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import co.edu.udistrital.mdp.adopcion.entities.BaseEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionApplicationEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.MedicalEventEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.medical.VaccineCardEntity;
import co.edu.udistrital.mdp.adopcion.entities.multimedia.MultimediaEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.ShelterArrivalEntity;
import co.edu.udistrital.mdp.adopcion.entities.ShelterEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
import lombok.Data;

@Data
@Entity
public class PetEntity extends BaseEntity {

private String name;
private Date birthDate;
private String breed;
private SizeEnum size;
private GenderEnum gender;
private String behaviorProfile;

    @PodamExclude
    @ManyToMany(mappedBy="pets")
    private List<OwnerEntity> owners = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "pet")
    private final List<AdoptionApplicationEntity> adoptionApplications = new ArrayList<>();

    //List events
    @PodamExclude
    @OneToMany(mappedBy = "pet")
    private List<MedicalEventEntity> medicalEvents = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "pet")
    private List<MultimediaEntity> multimedia = new ArrayList<>();

    @PodamExclude
    @OneToOne(mappedBy = "pet")
    private AdoptionEntity adoption;
    @OneToOne(mappedBy = "pet")
    private ShelterArrivalEntity shelterArrival;

    @PodamExclude
    @ManyToOne
    private ShelterEntity shelter;

    @PodamExclude
    @OneToOne
    private VaccineCardEntity vaccineCard;
    public List<AdoptionApplicationEntity> getAdoptionApplications() {
        return adoptionApplications;
    }
}
