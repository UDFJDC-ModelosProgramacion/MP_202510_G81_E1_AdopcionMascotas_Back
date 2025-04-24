package co.edu.udistrital.mdp.adopcion.entities.person;

import java.time.DayOfWeek;
import java.time.LocalTime;

import co.edu.udistrital.mdp.adopcion.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class DisponibilityEntity extends BaseEntity {
    private DayOfWeek day;
    private LocalTime startHour;
    private LocalTime endHour;

    @PodamExclude
    @ManyToOne
    private VeterinarianEntity veterinarian;
}
