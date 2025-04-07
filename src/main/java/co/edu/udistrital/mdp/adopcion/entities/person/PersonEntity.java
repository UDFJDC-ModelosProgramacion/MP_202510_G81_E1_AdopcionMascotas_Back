package co.edu.udistrital.mdp.adopcion.entities.person;


import co.edu.udistrital.mdp.adopcion.entities.BaseEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@MappedSuperclass
public abstract class PersonEntity extends BaseEntity {
    @PodamExclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}