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

import co.edu.udistrital.mdp.adopcion.dto.person.OwnerDTO;
import co.edu.udistrital.mdp.adopcion.dto.person.OwnerDetailDTO;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.services.person.OwnerService;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private ModelMapper modelMapper;

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
        return modelMapper.map(ownerEntity, OwnerDTO.class);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ownerService.deleteOwner(id, true);
    }
}
