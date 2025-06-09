package co.edu.udistrital.mdp.adopcion.controllers.events.medical;

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

import co.edu.udistrital.mdp.adopcion.dto.events.medical.DewormingDTO;
import co.edu.udistrital.mdp.adopcion.dto.events.medical.DewormingDetailDTO;
import co.edu.udistrital.mdp.adopcion.entities.events.medical.DewormingEntity;
import co.edu.udistrital.mdp.adopcion.exceptions.EntityNotFoundException;
import co.edu.udistrital.mdp.adopcion.exceptions.IllegalOperationException;
import co.edu.udistrital.mdp.adopcion.services.events.medical.DewormingService;

@RestController
@RequestMapping("/dewormings")
public class DewormingController {


    @Autowired
    private DewormingService dewormingService;
    
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<DewormingDetailDTO> findAll() {
        List<DewormingEntity> dewormings = dewormingService.getAllDewormings();
        return modelMapper.map(dewormings, new TypeToken<List<DewormingDetailDTO>>() {
        }.getType());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public DewormingDetailDTO findOne(@PathVariable Long id) throws EntityNotFoundException {
        DewormingEntity dewormingEntity = dewormingService.getDewormingById(id);
        return modelMapper.map(dewormingEntity, DewormingDetailDTO.class);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public DewormingDTO create(@RequestBody DewormingDTO dewormingDTO) throws IllegalOperationException, EntityNotFoundException {
        DewormingEntity dewormingEntity = dewormingService.createDeworming(modelMapper.map(dewormingDTO, DewormingEntity.class));
        return modelMapper.map(dewormingEntity, DewormingDTO.class);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public DewormingDTO update(@PathVariable Long id, @RequestBody DewormingDTO dewormingDTO) 
            throws EntityNotFoundException, IllegalOperationException {
        DewormingEntity dewormingEntity = dewormingService.updateDeworming(id, modelMapper.map(dewormingDTO, DewormingEntity.class));
        return modelMapper.map(dewormingEntity, DewormingDTO.class);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws EntityNotFoundException, IllegalOperationException {
        dewormingService.deleteDeworming(id);
    }


}
