package co.edu.udistrital.mdp.adopcion.services.events.medical;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udistrital.mdp.adopcion.entities.events.medical.VaccineCardEntity;
import co.edu.udistrital.mdp.adopcion.repositories.event.medical.DewormingRepository;
import co.edu.udistrital.mdp.adopcion.repositories.event.medical.VaccineCardRepository;
import co.edu.udistrital.mdp.adopcion.repositories.event.medical.VaccineRepository;
import co.edu.udistrital.mdp.adopcion.repositories.pet.PetRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VaccineCardService {
    @Autowired
    private VaccineCardRepository vaccineCardRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private DewormingRepository dewormingRepository;

    @Autowired
    private PetRepository petRepository;

    @Transactional
    public VaccineCardEntity createVaccineCard(VaccineCardEntity vaccineCard) {
        if (vaccineCard.getPet() == null) {
            throw new IllegalArgumentException("Pet must not be null");
        }
        return vaccineCardRepository.save(vaccineCard);
    }

    @Transactional
    public List<VaccineCardEntity> getAllVaccineCards() {
        return vaccineCardRepository.findAll();
    }

    @Transactional
    public VaccineCardEntity getVaccineCardById(Long id) {
        return vaccineCardRepository.findById(id).orElse(null);
    }

    @Transactional
    public VaccineCardEntity updateVaccineCard(Long id, VaccineCardEntity vaccineCard) {
        if (vaccineCardRepository.existsById(id)) {
            vaccineCard.setId(id);
            return vaccineCardRepository.save(vaccineCard);
        } else {
            return null;
        }
    }

    public void deleteVaccineCard(Long id) {
        vaccineCardRepository.deleteById(id);
    }

}
