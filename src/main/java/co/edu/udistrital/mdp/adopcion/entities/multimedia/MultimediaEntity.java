package co.edu.udistrital.mdp.adopcion.entities.multimedia;

import co.edu.udistrital.mdp.adopcion.entities.BaseEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import uk.co.jemos.podam.common.PodamExclude;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class MultimediaEntity extends BaseEntity {
    private String url;
    private String description;
    private MultimediaTypeEnum multimediaType;

    @PodamExclude
    @ManyToOne
    private PetEntity pet;
    private ShelterEntity shelter;
}
