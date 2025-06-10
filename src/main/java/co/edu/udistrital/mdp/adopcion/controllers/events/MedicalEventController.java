package co.edu.udistrital.mdp.adopcion.controllers.events;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udistrital.mdp.adopcion.dto.events.MedicalEventDTO;
import co.edu.udistrital.mdp.adopcion.dto.events.MedicalEventDetailDTO;
import co.edu.udistrital.mdp.adopcion.entities.events.MedicalEventEntity;
import co.edu.udistrital.mdp.adopcion.services.events.MedicalEventService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.udistrital.mdp.adopcion.exceptions.EntityNotFoundException;
import co.edu.udistrital.mdp.adopcion.exceptions.IllegalOperationException;

@RestController
@RequestMapping("/medical-events")
public class MedicalEventController {
    @Autowired
    private MedicalEventService medicalEventService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get all medical events.
     * 
     * @return
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<MedicalEventDetailDTO> findAll() {
        List<MedicalEventEntity> medicalEvents = medicalEventService.getAllMedicalEvents();
        return modelMapper.map(medicalEvents, new TypeToken<List<MedicalEventDetailDTO>>() {
        }.getType());
    }

    /**
     * Get medical event by ID.
     * 
     * @param medical_event_id
     * @return
     */
    @GetMapping(value = "/{medical_event_id}")
    @ResponseStatus(code = HttpStatus.OK)
    public MedicalEventDetailDTO findOne(@PathVariable Long medical_event_id) throws EntityNotFoundException {
        MedicalEventEntity medicalEventEntity = medicalEventService.getMedicalEventById(medical_event_id);
        return modelMapper.map(medicalEventEntity, MedicalEventDetailDTO.class);
    }

    /**
     * Create a new medical event.
     * 
     * @param medicalEventDTO
     * @return
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public MedicalEventDetailDTO create(@RequestBody MedicalEventDTO medicalEventDTO) throws EntityNotFoundException, IllegalOperationException {
        MedicalEventEntity medicalEventEntity = medicalEventService.createMedicalEvent(modelMapper.map(medicalEventDTO, MedicalEventEntity.class));
        return modelMapper.map(medicalEventEntity, MedicalEventDetailDTO.class);
    }

    /**
     * Update an existing medical event.
     * 
     * @param medical_event_id
     * @param medicalEventDTO
     * @return
     */
    @PutMapping(value = "/{medical_event_id}")
    @ResponseStatus(code = HttpStatus.OK)
    public MedicalEventDetailDTO update(@PathVariable Long medical_event_id, @RequestBody MedicalEventDTO medicalEventDTO) throws EntityNotFoundException, IllegalOperationException {
        MedicalEventEntity medicalEventEntity = medicalEventService.updateMedicalEvent(medical_event_id, modelMapper.map(medicalEventDTO, MedicalEventEntity.class));
        return modelMapper.map(medicalEventEntity, MedicalEventDetailDTO.class);
    }

    /**
     * Delete a medical event.
     * 
     * @param medical_event_id
     */
    @DeleteMapping(value = "/{medical_event_id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long medical_event_id) throws EntityNotFoundException, IllegalOperationException {
        medicalEventService.deleteMedicalEvent(medical_event_id);
    }
}
