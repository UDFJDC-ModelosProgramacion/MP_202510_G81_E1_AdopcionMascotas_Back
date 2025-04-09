package co.edu.udistrital.mdp.adopcion.entities.multimedia;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class MultimediaEntity {
    String url;
    String description;
    MultimediaType MultimediaType;
}
