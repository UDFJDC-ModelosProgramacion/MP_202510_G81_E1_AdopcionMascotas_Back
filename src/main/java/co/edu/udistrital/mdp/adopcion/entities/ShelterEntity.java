package co.edu.udistrital.mdp.adopcion.entities;

import java.util.List;

import co.edu.udistrital.mdp.adopcion.entities.events.ShelterEventEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class ShelterEntity extends BaseEntity{
    private String name;
    private String address;
    private String phone;
    private String email;

    @PodamExclude
    @OneToMany(mappedBy = "shelter")
    private List<ShelterEventEntity> shelterEvents;
}
