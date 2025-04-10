package co.edu.udistrital.mdp.adopcion.entities.person;


import java.util.ArrayList;
import java.util.List;

import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionApplicationEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionTestEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;


@Data
@Entity

public class OwnerEntity extends PersonEntity {
    @PodamExclude
    @OneToMany(mappedBy="owner")
	private List<AdoptionEntity> adoption = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy="owner")
	private List<AdoptionTestEntity> adoptionTests = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy="owner")
    private List<AdoptionApplicationEntity> adoptionApplications = new ArrayList<>();

    private HouseTypeEnum houseType;
    private String address;
}
