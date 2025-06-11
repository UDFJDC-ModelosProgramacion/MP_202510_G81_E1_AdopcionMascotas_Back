package co.edu.udistrital.mdp.adopcion.controllers.person;

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

import co.edu.udistrital.mdp.adopcion.dto.adoption.AdoptionAplicationDTO;
import co.edu.udistrital.mdp.adopcion.dto.adoption.AdoptionAplicationDetailDTO;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionApplicationEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import co.edu.udistrital.mdp.adopcion.exceptions.EntityNotFoundException;
import co.edu.udistrital.mdp.adopcion.exceptions.IllegalOperationException;
import co.edu.udistrital.mdp.adopcion.services.adoption.AdoptionApplicationService;
import co.edu.udistrital.mdp.adopcion.services.person.VeterinarianService;

@RestController
@RequestMapping("/veterinarians")
public class VeterinarianAdoptionAplicationController {
    @Autowired
    private VeterinarianService veterinarianService;
    @Autowired
    private AdoptionApplicationService adoptionAplicationService;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get all adoption applications for a specific veterinarian.
     *
     * @param veterinarianId
     * @return
     * @throws EntityNotFoundException
     */
    @GetMapping("/{veterinarianId}/adoption-applications")
    @ResponseStatus(code = HttpStatus.OK)
    public List<AdoptionAplicationDetailDTO> findAll(@PathVariable Long veterinarianId)
            throws EntityNotFoundException, IllegalOperationException {
        VeterinarianEntity veterinarian = veterinarianService.getVeterinarianById(veterinarianId);
        if (veterinarian == null) {
            throw new EntityNotFoundException("Veterinarian not found with ID: " + veterinarianId);
        }
        List<AdoptionApplicationEntity> adoptionApplications = adoptionAplicationService.getAllApplications();
        adoptionApplications.removeIf(application -> !application.getVeterinarian().getId().equals(veterinarianId));
        if (adoptionApplications.isEmpty()) {
            throw new IllegalOperationException("No adoption applications found for veterinarian with ID: " + veterinarianId);
        }
        return modelMapper.map(adoptionApplications, new TypeToken<List<AdoptionAplicationDetailDTO>>() {
        }.getType());
    }

    /**
     * Get adoption application by ID for a specific veterinarian.
     *
     * @param veterinarianId
     * @param adoptionAplicationId
     * @return
     * @throws EntityNotFoundException
     */
    @GetMapping("/{veterinarianId}/adoption-applications/{adoptionAplicationId}")
    @ResponseStatus(code = HttpStatus.OK)
    public AdoptionAplicationDetailDTO findOne(@PathVariable Long veterinarianId, @PathVariable Long adoptionAplicationId)
            throws EntityNotFoundException, IllegalOperationException {
        VeterinarianEntity veterinarian = veterinarianService.getVeterinarianById(veterinarianId);
        if (veterinarian == null) {
            throw new EntityNotFoundException("Veterinarian not found with ID: " + veterinarianId);
        }
        AdoptionApplicationEntity adoptionAplicationEntity = adoptionAplicationService.getApplicationById(adoptionAplicationId);
        if (adoptionAplicationEntity == null || !adoptionAplicationEntity.getVeterinarian().getId().equals(veterinarianId)) {
            throw new IllegalOperationException(
                    "In this veterinarian, adoption application not found with ID: " + adoptionAplicationId);
        }
        return modelMapper.map(adoptionAplicationEntity, AdoptionAplicationDetailDTO.class);
    }

    /**
     * Create a new adoption application for a specific veterinarian.
     *
     * @param veterinarianId
     * @param listAdoptionAplicationDTO
     * @return
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
     */
    @PutMapping("/{veterinarianId}/adoption-applications")
    @ResponseStatus(code = HttpStatus.OK)
    public List<AdoptionAplicationDetailDTO> replaceApplications(@RequestBody List<AdoptionAplicationDTO> listAdoptionAplicationDTO,
            @PathVariable Long veterinarianId)
            throws EntityNotFoundException, IllegalOperationException {
        VeterinarianEntity veterinarian = veterinarianService.getVeterinarianById(veterinarianId);
        if (veterinarian == null) {
            throw new EntityNotFoundException("Veterinarian not found with ID: " + veterinarianId);
        }
        List<AdoptionApplicationEntity> adoptionAplicationEntities = modelMapper.map(listAdoptionAplicationDTO,
                new TypeToken<List<AdoptionApplicationEntity>>() {
                }.getType());
        adoptionAplicationEntities.forEach(application -> application.setVeterinarian(veterinarian));
        for (AdoptionApplicationEntity application : adoptionAplicationEntities) {
            try {
                application = adoptionAplicationService.updateApplication(application.getId(), application);
            } catch (IllegalArgumentException e) {
                throw new EntityNotFoundException(
                        "Failed to update, not found adoption application with ID: " + application.getId());
            }
        }
        adoptionAplicationEntities = adoptionAplicationService.getAllApplications();
        return modelMapper.map(adoptionAplicationEntities, new TypeToken<List<AdoptionAplicationDetailDTO>>() {
        }.getType());
    }

    /**
     * Update an existing adoption application for a specific veterinarian.
     *
     * @param veterinarianId
     * @param adoptionAplicationId
     * @return
     * @throws EntityNotFoundException
     * @throws IllegalOperationException
     */
    @PostMapping("/{veterinarianId}/adoption-applications/{adoptionAplicationId}")
    @ResponseStatus(code = HttpStatus.OK)
    public AdoptionAplicationDetailDTO addApplication(@PathVariable Long veterinarianId, @PathVariable Long adoptionAplicationId)
            throws EntityNotFoundException, IllegalOperationException {
        VeterinarianEntity veterinarian = veterinarianService.getVeterinarianById(veterinarianId);
        if (veterinarian == null) {
            throw new EntityNotFoundException("Veterinarian not found with ID: " + veterinarianId);
        }
        AdoptionApplicationEntity adoptionAplicationEntity = adoptionAplicationService.getApplicationById(adoptionAplicationId);
        if (adoptionAplicationEntity == null || !adoptionAplicationEntity.getVeterinarian().getId().equals(veterinarianId)) {
            throw new EntityNotFoundException("Adoption application not found with ID: " + adoptionAplicationId);
        }
        adoptionAplicationEntity.setVeterinarian(veterinarian);
        adoptionAplicationEntity = adoptionAplicationService.updateApplication(adoptionAplicationId, adoptionAplicationEntity);
        if (adoptionAplicationEntity == null) {
            throw new IllegalOperationException(
                    "Failed to update, not found adoption application with ID: " + adoptionAplicationId);
        }
        return modelMapper.map(adoptionAplicationEntity, AdoptionAplicationDetailDTO.class);
    }

    /**
     * Delete an adoption application by ID for a specific veterinarian.
     *
     * @param veterinarianId
     * @param adoptionAplicationId
     * @throws EntityNotFoundException
     */
    @DeleteMapping("/{veterinarianId}/adoption-applications/{adoptionAplicationId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long veterinarianId, @PathVariable Long adoptionAplicationId)
            throws EntityNotFoundException, IllegalOperationException {
        VeterinarianEntity veterinarian = veterinarianService.getVeterinarianById(veterinarianId);
        if (veterinarian == null) {
            throw new EntityNotFoundException("Veterinarian not found with ID: " + veterinarianId);
        }
        AdoptionApplicationEntity adoptionAplicationEntity = adoptionAplicationService.getApplicationById(adoptionAplicationId);
        if (adoptionAplicationEntity == null || !adoptionAplicationEntity.getVeterinarian().getId().equals(veterinarianId)) {
            throw new IllegalOperationException(
                    "In this veterinarian, adoption application not found with ID: " + adoptionAplicationId);
        }
        adoptionAplicationService.deleteApplication(adoptionAplicationId);
    }
}