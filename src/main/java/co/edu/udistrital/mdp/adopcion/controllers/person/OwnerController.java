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
import org.springframework.web.server.ResponseStatusException;

import co.edu.udistrital.mdp.adopcion.dto.person.OwnerDTO;
import co.edu.udistrital.mdp.adopcion.dto.person.OwnerDetailDTO;
import co.edu.udistrital.mdp.adopcion.dto.adoption.AdoptionDTO;
import co.edu.udistrital.mdp.adopcion.dto.adoption.AdoptionTestDTO;
import co.edu.udistrital.mdp.adopcion.dto.adoption.AdoptionApplicationDTO;
import co.edu.udistrital.mdp.adopcion.dto.pet.PetDTO;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import co.edu.udistrital.mdp.adopcion.services.person.OwnerService;
import co.edu.udistrital.mdp.adopcion.services.pet.PetService;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private PetService petService;

    @Autowired
    private ModelMapper modelMapper;

    // CRUD básico para Owner
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<OwnerDetailDTO> findAll() {
        List<OwnerEntity> owners = ownerService.getOwners();
        return modelMapper.map(owners, new TypeToken<List<OwnerDetailDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public OwnerDetailDTO findOne(@PathVariable Long id) {
        OwnerEntity ownerEntity = ownerService.getOwner(id);
        if (ownerEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found with ID: " + id);
        }
        return modelMapper.map(ownerEntity, OwnerDetailDTO.class);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public OwnerDTO create(@RequestBody OwnerDTO ownerDTO) {
        OwnerEntity ownerEntity = ownerService.createOwner(modelMapper.map(ownerDTO, OwnerEntity.class));
        return modelMapper.map(ownerEntity, OwnerDTO.class);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public OwnerDTO update(@PathVariable Long id, @RequestBody OwnerDTO ownerDTO) {
        OwnerEntity ownerEntity = ownerService.updateOwner(id, modelMapper.map(ownerDTO, OwnerEntity.class), false);
        if (ownerEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found with ID: " + id);
        }
        return modelMapper.map(ownerEntity, OwnerDTO.class);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        try {
            ownerService.deleteOwner(id, true);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found with ID: " + id);
        } catch (SecurityException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    // Métodos para Adoptions
    @GetMapping("/{ownerId}/adoptions")
    @ResponseStatus(code = HttpStatus.OK)
    public List<AdoptionDTO> getAdoptions(@PathVariable Long ownerId) {
        OwnerEntity owner = ownerService.getOwner(ownerId);
        if (owner == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found with ID: " + ownerId);
        }
        return modelMapper.map(owner.getAdoptions(), new TypeToken<List<AdoptionDTO>>() {}.getType());
    }

    // Métodos para Adoption Tests
    @GetMapping("/{ownerId}/adoption-tests")
    @ResponseStatus(code = HttpStatus.OK)
    public List<AdoptionTestDTO> getAdoptionTests(@PathVariable Long ownerId) {
        OwnerEntity owner = ownerService.getOwner(ownerId);
        if (owner == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found with ID: " + ownerId);
        }
        return modelMapper.map(owner.getAdoptionTests(), new TypeToken<List<AdoptionTestDTO>>() {}.getType());
    }

    // Métodos para Adoption Applications
    @GetMapping("/{ownerId}/adoption-applications")
    @ResponseStatus(code = HttpStatus.OK)
    public List<AdoptionApplicationDTO> getAdoptionApplications(@PathVariable Long ownerId) {
        OwnerEntity owner = ownerService.getOwner(ownerId);
        if (owner == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner not found with ID: " + ownerId);
        }
        return modelMapper.map(owner.getAdoptionApplications(), new TypeToken<List<AdoptionApplicationDTO>>() {}.getType());
    }
}