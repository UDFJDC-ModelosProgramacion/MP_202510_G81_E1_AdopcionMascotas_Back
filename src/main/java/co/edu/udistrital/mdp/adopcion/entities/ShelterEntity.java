package co.edu.udistrital.mdp.adopcion.entities;

import jakarta.persistence.Entity;
import lombok.Data;
@Data
@Entity
public class ShelterEntity extends BaseEntity{
private String name;
private String address;
private String phone;
private String email;
}
