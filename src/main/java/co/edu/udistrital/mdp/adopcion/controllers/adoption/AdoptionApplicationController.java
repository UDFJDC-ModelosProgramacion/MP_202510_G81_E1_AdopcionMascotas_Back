package co.edu.udistrital.mdp.adopcion.controllers.adoption;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import co.edu.udistrital.mdp.adopcion.dto.adoption.AdoptionApplicationDTO;
import co.edu.udistrital.mdp.adopcion.dto.adoption.AdoptionApplicationDetailDTO;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionApplicationEntity;
import co.edu.udistrital.mdp.adopcion.exceptions.EntityNotFoundException;
import co.edu.udistrital.mdp.adopcion.exceptions.IllegalOperationException;
import co.edu.udistrital.mdp.adopcion.services.adoption.AdoptionApplicationService;

@RestController
@RequestMapping("/adoption-applications")
public class AdoptionApplicationController {

    @Autowired
    private AdoptionApplicationService applicationService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AdoptionApplicationDetailDTO> findAll() {
        List<AdoptionApplicationEntity> apps = applicationService.getAllAdoptionApplications();
        return modelMapper.map(apps, new TypeToken<List<AdoptionApplicationDetailDTO>>() {}.getType());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdoptionApplicationDetailDTO findOne(@PathVariable Long id) throws EntityNotFoundException {
        AdoptionApplicationEntity entity = applicationService.getAdoptionApplicationById(id);
        return modelMapper.map(entity, AdoptionApplicationDetailDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdoptionApplicationDTO create(@RequestBody AdoptionApplicationDTO dto)
            throws IllegalOperationException, EntityNotFoundException {
        AdoptionApplicationEntity entity = applicationService.createAdoptionApplication(
                modelMapper.map(dto, AdoptionApplicationEntity.class));
        return modelMapper.map(entity, AdoptionApplicationDTO.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdoptionApplicationDTO update(@PathVariable Long id, @RequestBody AdoptionApplicationDTO dto)
            throws EntityNotFoundException, IllegalOperationException {
        AdoptionApplicationEntity entity = applicationService.updateAdoptionApplication(id,
                modelMapper.map(dto, AdoptionApplicationEntity.class));
        return modelMapper.map(entity, AdoptionApplicationDTO.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
        applicationService.deleteAdoptionApplication(id);
    }
}
