package co.edu.udistrital.mdp.adopcion.entities.person;

import java.time.DayOfWeek;
import java.time.LocalTime;

import uk.co.jemos.podam.common.PodamExclude;

public class DisponibilityEntity {
    DayOfWeek day;
    LocalTime startHour;
    LocalTime endHour;

    @PodamExclude
    @ManyToOne
    VeterinarianEntity veterinarian;
}
