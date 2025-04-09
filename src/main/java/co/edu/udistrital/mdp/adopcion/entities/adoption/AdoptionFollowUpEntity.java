package co.edu.udistrital.mdp.adopcion.entities.adoption;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class AdoptionFollowUpEntity {
    String description;
    String observations;
    FollowUpStatusEnum followUpStatus;
    
}
