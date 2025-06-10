package co.edu.udistrital.mdp.adopcion.controllers.events;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udistrital.mdp.adopcion.dto.events.ShelterArrivalDTO;
import co.edu.udistrital.mdp.adopcion.dto.events.ShelterArrivalDetailDTO;
import co.edu.udistrital.mdp.adopcion.entities.ShelterEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.ShelterArrivalEntity;
import co.edu.udistrital.mdp.adopcion.exceptions.EntityNotFoundException;
import co.edu.udistrital.mdp.adopcion.exceptions.IllegalOperationException;
import co.edu.udistrital.mdp.adopcion.services.ShelterService;
import co.edu.udistrital.mdp.adopcion.services.events.ShelterArrivalService;


@RestController
@RequestMapping("/shelters")
public class ShelterShelterArrivalController {
    @Autowired
    private ShelterService shelterService;
    @Autowired
    private ShelterArrivalService shelterArrivalService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get all shelter arrivals for a specific shelter.
     * 
     * @param shelterId
     * @return
     * @throws EntityNotFoundException
     */
    @GetMapping("/{shelterId}/arrivals")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ShelterArrivalDetailDTO> findAll(@PathVariable Long shelterId) throws EntityNotFoundException {
        ShelterEntity shelter = shelterService.getShelterById(shelterId);
        if (shelter == null) {
            throw new EntityNotFoundException("Shelter not found with ID: " + shelterId);
        }
        List<ShelterArrivalEntity> shelterArrivals = shelterArrivalService.getAllShelterArrivals();
        shelterArrivals.removeIf(arrival -> !arrival.getShelter().getId().equals(shelterId));
        if (shelterArrivals.isEmpty()) {
            throw new EntityNotFoundException("No shelter arrivals found for shelter with ID: " + shelterId);
        }
        return modelMapper.map(shelterArrivals, new TypeToken<List<ShelterArrivalDetailDTO>>() {
        }.getType());
    }

    /**
     * Get shelter arrival by ID for a specific shelter.
     * 
     * @param shelterId
     * @param shelterArrivalId
     * @return
     * @throws EntityNotFoundException
     */
    @GetMapping("/{shelterId}/arrivals/{shelterArrivalId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ShelterArrivalDetailDTO findOne(@PathVariable Long shelterId, @PathVariable Long shelterArrivalId)
            throws EntityNotFoundException {
        ShelterEntity shelter = shelterService.getShelterById(shelterId);
        if (shelter == null) {
            throw new EntityNotFoundException("Shelter not found with ID: " + shelterId);
        }
        ShelterArrivalEntity shelterArrivalEntity = shelterArrivalService.getShelterArrivalById(shelterArrivalId);
        if (shelterArrivalEntity == null || !shelterArrivalEntity.getShelter().getId().equals(shelterId)) {
            throw new EntityNotFoundException("Shelter arrival not found with ID: " + shelterArrivalId);
        }
        return modelMapper.map(shelterArrivalEntity, ShelterArrivalDetailDTO.class);
    }

    /**
     * Create a new shelter arrival for a specific shelter.
     * @param shelterId
     * @param shelterArrivalDTO
     * @return
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
     */
    @PostMapping("/{shelterId}/arrivals")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ShelterArrivalDTO postMethodName(@RequestBody ShelterArrivalDTO shelterArrivalDTO, @PathVariable Long shelterId)
            throws EntityNotFoundException, IllegalOperationException {
        ShelterEntity shelter = shelterService.getShelterById(shelterId);
        if (shelter == null) {
            throw new EntityNotFoundException("Shelter not found with ID: " + shelterId);
        }

        ShelterArrivalEntity shelterArrivalEntity = modelMapper.map(shelterArrivalDTO, ShelterArrivalEntity.class);
        shelterArrivalEntity.setShelter(shelter);
        shelterArrivalEntity = shelterArrivalService.createShelterArrival(shelterArrivalEntity);
        return modelMapper.map(shelterArrivalEntity, ShelterArrivalDTO.class);
    }
    
    /**
     * Update an existing shelter arrival for a specific shelter.
     * 
     * @param shelterId
     * @param shelterArrivalId
     * @param shelterArrivalDTO
     * @return
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
     */
    @PostMapping("/{shelterId}/arrivals/{shelterArrivalId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ShelterArrivalDTO putMethodName(@PathVariable Long shelterId, @PathVariable Long shelterArrivalId) throws EntityNotFoundException, IllegalOperationException {
        ShelterEntity shelter = shelterService.getShelterById(shelterId);
        if (shelter == null) {
            throw new EntityNotFoundException("Shelter not found with ID: " + shelterId);
        }
        ShelterArrivalEntity shelterArrivalEntity = shelterArrivalService.getShelterArrivalById(shelterArrivalId);
        if (shelterArrivalEntity == null || !shelterArrivalEntity.getShelter().getId().equals(shelterId)) {
            throw new EntityNotFoundException("Shelter arrival not found with ID: " + shelterArrivalId);
        }
        shelterArrivalEntity.setShelter(shelter);
        shelterArrivalEntity = shelterArrivalService.updateShelterArrival(shelterArrivalId, shelterArrivalEntity);
        if (shelterArrivalEntity == null) {
            throw new IllegalOperationException("Failed to update shelter arrival with ID: " + shelterArrivalId);
        }
        return modelMapper.map(shelterArrivalEntity, ShelterArrivalDTO.class);
    }

    /**
     * Delete a shelter arrival by ID for a specific shelter.
     * 
     * @param shelterId
     * @param shelterArrivalId
     * @throws EntityNotFoundException
     */
    @PostMapping("/{shelterId}/arrivals/{shelterArrivalId}/delete")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long shelterId, @PathVariable Long shelterArrivalId)
            throws EntityNotFoundException, IllegalOperationException {
        ShelterEntity shelter = shelterService.getShelterById(shelterId);
        if (shelter == null) {
            throw new EntityNotFoundException("Shelter not found with ID: " + shelterId);
        }
        ShelterArrivalEntity shelterArrivalEntity = shelterArrivalService.getShelterArrivalById(shelterArrivalId);
        if (shelterArrivalEntity == null || !shelterArrivalEntity.getShelter().getId().equals(shelterId)) {
            throw new EntityNotFoundException("Shelter arrival not found with ID: " + shelterArrivalId);
        }
        shelterArrivalService.deleteShelterArrival(shelterArrivalId);
    }
}
