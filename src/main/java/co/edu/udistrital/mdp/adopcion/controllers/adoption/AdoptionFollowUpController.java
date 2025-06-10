package co.edu.udistrital.mdp.adopcion.controllers.adoption;

import co.edu.udistrital.mdp.adopcion.dto.adoption.AdoptionFollowUpDTO;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionFollowUpEntity;
import co.edu.udistrital.mdp.adopcion.exceptions.EntityNotFoundException;
import co.edu.udistrital.mdp.adopcion.exceptions.IllegalOperationException;
import co.edu.udistrital.mdp.adopcion.services.adoption.AdoptionFollowUpService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/adoption-followups")
public class AdoptionFollowUpController {

    private final AdoptionFollowUpService followUpService;
    private final ModelMapper modelMapper;

    public AdoptionFollowUpController(AdoptionFollowUpService followUpService, ModelMapper modelMapper) {
        this.followUpService = followUpService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<AdoptionFollowUpDTO> createFollowUp(@RequestBody AdoptionFollowUpDTO dto) throws IllegalOperationException {
        AdoptionFollowUpEntity entity = modelMapper.map(dto, AdoptionFollowUpEntity.class);
        AdoptionFollowUpEntity created = followUpService.createFollowUp(entity);
        return new ResponseEntity<>(modelMapper.map(created, AdoptionFollowUpDTO.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AdoptionFollowUpDTO>> getAllFollowUps() {
        List<AdoptionFollowUpDTO> followUps = followUpService.getAllFollowUps().stream()
                .map(fu -> modelMapper.map(fu, AdoptionFollowUpDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(followUps);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionFollowUpDTO> getFollowUpById(@PathVariable Long id) throws EntityNotFoundException {
        AdoptionFollowUpEntity followUp = followUpService.getFollowUpById(id);
        return ResponseEntity.ok(modelMapper.map(followUp, AdoptionFollowUpDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptionFollowUpDTO> updateFollowUp(@PathVariable Long id, @RequestBody AdoptionFollowUpDTO dto) throws EntityNotFoundException {
        AdoptionFollowUpEntity entity = modelMapper.map(dto, AdoptionFollowUpEntity.class);
        AdoptionFollowUpEntity updated = followUpService.updateFollowUp(id, entity);
        return ResponseEntity.ok(modelMapper.map(updated, AdoptionFollowUpDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollowUp(@PathVariable Long id) throws EntityNotFoundException {
        followUpService.deleteFollowUp(id);
        return ResponseEntity.noContent().build();
    }
}