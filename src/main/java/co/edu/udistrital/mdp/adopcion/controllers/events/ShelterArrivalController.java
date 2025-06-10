package co.edu.udistrital.mdp.adopcion.controllers.events;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udistrital.mdp.adopcion.dto.events.ShelterArrivalDetailDTO;
import co.edu.udistrital.mdp.adopcion.entities.events.ShelterArrivalEntity;
import co.edu.udistrital.mdp.adopcion.services.events.ShelterArrivalService;

@RestController
@RequestMapping("/shelter-arrivals")
public class ShelterArrivalController {
    @Autowired
    private ShelterArrivalService shelterArrivalService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get all shelter arrivals.
     * 
     * @return
     */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ShelterArrivalDetailDTO> findAll() {
        List<ShelterArrivalEntity> shelterArrivals = shelterArrivalService.getAllShelterArrivals();
        return modelMapper.map(shelterArrivals, new TypeToken<List<ShelterArrivalDetailDTO>>() {
        }.getType());
    }

    /**
     * Get shelter arrival by ID.
     * 
     * @param shelter_arrival_id
     * @return
     */
    @GetMapping("/{shelter_arrival_id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ShelterArrivalDetailDTO findOne(@PathVariable Long shelter_arrival_id) {
        ShelterArrivalEntity shelterArrivalEntity = shelterArrivalService.getShelterArrivalById(shelter_arrival_id);
        return modelMapper.map(shelterArrivalEntity, ShelterArrivalDetailDTO.class);
    }

    /**
     * Create a new shelter arrival.
     * @param shelterArrivalDTO
     */
}