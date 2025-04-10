package co.edu.udistrital.mdp.adopcion.entities.person;


import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionTestEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;


@Data
@Entity

public class OwnerEntity extends PersonEntity {
    @PodamExclude
    @ManyToOne
	private  AdoptionEntity adoption;

    @PodamExclude
    @ManyToOne
	private  AdoptionTestEntity adoptionTest;

    private HouseTypeEnum houseType;
    private String address;
}
