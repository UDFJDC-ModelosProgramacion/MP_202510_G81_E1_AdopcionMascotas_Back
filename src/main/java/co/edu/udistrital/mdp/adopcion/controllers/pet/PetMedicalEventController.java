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

import co.edu.udistrital.mdp.adopcion.exceptions.EntityNotFoundException;
import co.edu.udistrital.mdp.adopcion.exceptions.IllegalOperationException;

import co.edu.udistrital.mdp.adopcion.dto.events.MedicalEventDTO;
import co.edu.udistrital.mdp.adopcion.dto.events.MedicalEventDetailDTO;
import co.edu.udistrital.mdp.adopcion.services.pet.PetService;
import co.edu.udistrital.mdp.adopcion.services.events.MedicalEventService;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.MedicalEventEntity;

@RestController
@RequestMapping("/pets")
public class PetMedicalEventController {
    @Autowired
    private PetService petService;

    @Autowired
    private MedicalEventService medicalEventService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get all medical events for a specific pet.
     * 
     * @param petId
     * @return List of MedicalEventDetailDTO
     * @throws EntityNotFoundException
     */
    @GetMapping("/{petId}/medical-events")
    @ResponseStatus(code = HttpStatus.OK)
    public List<MedicalEventDetailDTO> findAll(@PathVariable Long petId)
            throws EntityNotFoundException, IllegalOperationException {
        PetEntity pet = petService.getPet(petId);
        if (pet == null) {
            throw new EntityNotFoundException("Pet not found with ID: " + petId);
        }
        List<MedicalEventEntity> medicalEvents = medicalEventService.getAllMedicalEvents();
        medicalEvents.removeIf(event -> !event.getPet().getId().equals(petId));
        if (medicalEvents.isEmpty()) {
            throw new IllegalOperationException("No medical events found for pet");
        }
        return modelMapper.map(medicalEvents, new TypeToken<List<MedicalEventDetailDTO>>() {
        }.getType());
    }

    /**
     * Get a specific medical event by ID for a pet.
     * 
     * @param petId
     * @param eventId
     * @return MedicalEventDetailDTO
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
     */
    @GetMapping("/{petId}/medical-events/{eventId}")
    @ResponseStatus(code = HttpStatus.OK)
    public MedicalEventDetailDTO findById(@PathVariable Long petId, @PathVariable Long eventId)
            throws EntityNotFoundException, IllegalOperationException {
        PetEntity pet = petService.getPet(petId);
        if (pet == null) {
            throw new EntityNotFoundException("Pet not found with ID: " + petId);
        }
        MedicalEventEntity medicalEvent = medicalEventService.getMedicalEvent(eventId);
        if (medicalEvent == null || !medicalEvent.getPet().getId().equals(petId)) {
            throw new EntityNotFoundException("Medical event not found with ID: " + eventId);
        }
        return modelMapper.map(medicalEvent, MedicalEventDetailDTO.class);
    }

    /**
     * Create a new medical event for a specific pet.
     * 
     * @param petId
     * @param medicalEventId
     * @return MedicalEventDetailDTO
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
     */
    @PostMapping("/{petId}/medical-events")
    @ResponseStatus(code = HttpStatus.CREATED)
    public MedicalEventDetailDTO create(@PathVariable Long petId, @RequestBody MedicalEventDTO medicalEventDTO)
            throws EntityNotFoundException, IllegalOperationException {
        PetEntity pet = petService.getPet(petId);
        if (pet == null) {
            throw new EntityNotFoundException("Pet not found with ID: " + petId);
        }
        
    }

}
