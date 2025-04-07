package co.edu.udistrital.mdp.adopcion.entities.person;

import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity

public class OwnerEntity extends PersonEntity {

    private HouseTypeEnum houseType;
    private String address;


}
