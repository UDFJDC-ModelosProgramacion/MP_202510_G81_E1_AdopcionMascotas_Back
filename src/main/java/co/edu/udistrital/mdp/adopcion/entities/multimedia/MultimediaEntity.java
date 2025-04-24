package co.edu.udistrital.mdp.adopcion.entities.multimedia;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class MultimediaEntity {
    private String url;
    private String description;
    private MultimediaTypeEnum multimediaType;
}
