package co.edu.udistrital.mdp.adopcion.entities.person;

import java.util.ArrayList;
import java.util.List;

import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionAplicationEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionFollowUpEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionTestEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.MedicalEventEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class VeterinarianEntity extends PersonEntity {
    private String licenseNumber;
    private Speciality speciality;

    @PodamExclude
    @OneToMany(mappedBy = "veterinarian")
    private List<MedicalEventEntity> medicalEvents;

    @OneToMany(mappedBy = "veterinarian")
    private List<DisponibilityEntity> disponibilities;
    
    @PodamExclude
    @OneToMany(mappedBy="veterinarian")
    private List<AdoptionAplicationEntity> adoptionApplications = new ArrayList<>();

    @OneToMany(mappedBy = "veterinarian")
    private List<AdoptionFollowUpEntity> followUps;

    @PodamExclude
    @OneToOne(mappedBy="veterinarian")
    private  AdoptionEntity adoption;

    @PodamExclude
    @OneToMany(mappedBy="veterinarian")
    private List<AdoptionTestEntity> adoptionTests = new ArrayList<>();
}
