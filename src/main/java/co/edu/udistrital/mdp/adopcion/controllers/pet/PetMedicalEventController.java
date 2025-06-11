package co.edu.udistrital.mdp.adopcion.controllers.pet;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udistrital.mdp.adopcion.dto.events.MedicalEventDTO;
import co.edu.udistrital.mdp.adopcion.dto.events.MedicalEventDetailDTO;

@RestController
@RequestMapping("/pets")
public class PetMedicalEventController {
    @Autowired
    private PetService petService;

    @Autowired
    private MedicalEventService medicalEventService;

    @Autowired
    private ModelMapper modelMapper;
}
