package co.edu.udistrital.mdp.adopcion.controllers.adoption;

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

import co.edu.udistrital.mdp.adopcion.dto.adoption.AdoptionDTO;
import co.edu.udistrital.mdp.adopcion.dto.adoption.AdoptionDetailDTO;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionEntity;
import co.edu.udistrital.mdp.adopcion.exceptions.EntityNotFoundException;
import co.edu.udistrital.mdp.adopcion.exceptions.IllegalOperationException;
import co.edu.udistrital.mdp.adopcion.services.adoption.AdoptionService;

@RestController
@RequestMapping("/adoptions")
public class AdoptionController {

    @Autowired
    private AdoptionService adoptionService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<AdoptionDetailDTO> findAll() {
        List<AdoptionEntity> adoptions = adoptionService.getAllAdoptions();
        return modelMapper.map(adoptions, new TypeToken<List<AdoptionDetailDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public AdoptionDetailDTO findOne(@PathVariable Long id) throws EntityNotFoundException {
        AdoptionEntity adoptionEntity = adoptionService.getAdoptionById(id);
        if (adoptionEntity == null) {
            throw new EntityNotFoundException("The adoption with the given id was not found");
        }
        return modelMapper.map(adoptionEntity, AdoptionDetailDTO.class);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AdoptionDTO create(@RequestBody AdoptionDTO adoptionDTO) throws IllegalOperationException, EntityNotFoundException {
        AdoptionEntity adoptionEntity = adoptionService.createAdoption(modelMapper.map(adoptionDTO, AdoptionEntity.class));
        if (adoptionEntity == null) {
            throw new IllegalOperationException("Failed to create adoption");
        }
        return modelMapper.map(adoptionEntity, AdoptionDTO.class);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public AdoptionDTO update(@PathVariable Long id, @RequestBody AdoptionDTO adoptionDTO)
            throws EntityNotFoundException, IllegalOperationException {
        AdoptionEntity adoptionEntity = adoptionService.updateAdoption(id, modelMapper.map(adoptionDTO, AdoptionEntity.class));
        if (adoptionEntity == null) {
            throw new EntityNotFoundException("The adoption with the given id was not found");
        }
        return modelMapper.map(adoptionEntity, AdoptionDTO.class);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
        if (adoptionService.getAdoptionById(id) == null) {
            throw new EntityNotFoundException("The adoption with the given id was not found");
        }
        adoptionService.deleteAdoption(id);
    }
}