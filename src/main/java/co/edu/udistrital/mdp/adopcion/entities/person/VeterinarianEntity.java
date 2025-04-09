package co.edu.udistrital.mdp.adopcion.entities.person;

import java.util.List;

import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionFollowUpEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.MedicalEventEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class VeterinarianEntity extends PersonEntity {
    private String licenseNumber;
    private Speciality speciality;

    @PodamExclude
    @OneToMany(mappedBy = "medicalEvents")
    private List<MedicalEventEntity> medicalEvents;

    @OneToMany(mappedBy = "disponibilities")
    private List<DisponibilityEntity> disponibilities;

    @OneToMany(mappedBy = "followUps")
    private List<AdoptionFollowUpEntity> followUps;
    
}
