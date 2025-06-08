package co.edu.udistrital.mdp.adopcion.dto.events;

import lombok.Data;

@Data
public class MedicalEventDTO extends EventDTO {
    private VeterinarianDTO veterinarian;
    private PetDTO pet;
}
