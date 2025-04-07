package co.edu.udistrital.mdp.adopcion.entities.events;

import co.edu.udistrital.mdp.adopcion.entities.ShelterEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class ShelterEventEntity extends EventEntity {
    String name;
    ShelterEntity shelter;
}
