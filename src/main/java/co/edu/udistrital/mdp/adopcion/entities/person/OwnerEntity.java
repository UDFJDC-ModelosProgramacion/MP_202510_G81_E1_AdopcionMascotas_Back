package co.edu.udistrital.mdp.adopcion.entities.person;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;


@Data
@Entity

public class OwnerEntity extends PersonEntity {

    private HouseTypeEnum houseType;
    private String address;

    


}
