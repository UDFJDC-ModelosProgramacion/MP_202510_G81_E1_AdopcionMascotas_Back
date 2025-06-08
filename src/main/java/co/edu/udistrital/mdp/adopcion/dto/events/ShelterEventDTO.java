package co.edu.udistrital.mdp.adopcion.dto.events;

import lombok.Data;

@Data
public class ShelterEventDTO extends EventDTO {
    private String name;
    private ShelterDTO shelter;
}
