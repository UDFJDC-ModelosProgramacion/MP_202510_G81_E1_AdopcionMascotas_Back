package co.edu.udistrital.mdp.adopcion.controllers.adoption;

import co.edu.udistrital.mdp.adopcion.dto.adoption.AdoptionApplicationDTO;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionAplicationEntity;
import co.edu.udistrital.mdp.adopcion.exceptions.EntityNotFoundException;
import co.edu.udistrital.mdp.adopcion.services.adoption.AdoptionApplicationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/adoption-applications")
public class AdoptionApplicationController {

    private final AdoptionApplicationService service;
    private final ModelMapper modelMapper;

    public AdoptionApplicationController(AdoptionApplicationService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<AdoptionApplicationDTO> createApplication(@RequestBody AdoptionApplicationDTO dto) {
        AdoptionAplicationEntity entity = modelMapper.map(dto, AdoptionAplicationEntity.class);
        AdoptionAplicationEntity created = service.createApplication(entity);
        return new ResponseEntity<>(modelMapper.map(created, AdoptionApplicationDTO.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AdoptionApplicationDTO>> getAllApplications() {
        List<AdoptionApplicationDTO> list = service.getAllApplications()
                .stream().map(entity -> modelMapper.map(entity, AdoptionApplicationDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionApplicationDTO> getApplicationById(@PathVariable Long id) throws EntityNotFoundException {
        AdoptionAplicationEntity entity = service.getApplicationById(id);
        return ResponseEntity.ok(modelMapper.map(entity, AdoptionApplicationDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptionApplicationDTO> updateApplication(@PathVariable Long id, @RequestBody AdoptionApplicationDTO dto) throws EntityNotFoundException {
        AdoptionAplicationEntity entity = modelMapper.map(dto, AdoptionAplicationEntity.class);
        AdoptionAplicationEntity updated = service.updateApplication(id, entity);
        return ResponseEntity.ok(modelMapper.map(updated, AdoptionApplicationDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) throws EntityNotFoundException {
        service.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}
