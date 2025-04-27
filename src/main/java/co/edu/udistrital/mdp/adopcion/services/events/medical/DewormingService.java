package co.edu.udistrital.mdp.adopcion.services.events.medical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udistrital.mdp.adopcion.repositories.event.medical.DewormingRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DewormingService {
    @Autowired
    private final DewormingRepository dewormingRepository;

    @Autowired
    private final VaccineCardService vaccineCardService;

    @Transactional
    public DewormingEntity createDeworming(DewormingEntity deworming) {
        if (deworming.getName() == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        if (deworming.getBrandName() == null) {
            throw new IllegalArgumentException("Brand name must not be null");
        }
        if (deworming.getNextDate() == null) {
            throw new IllegalArgumentException("Next date must not be null");
        }
        if (deworming.getDosis() == null) {
            throw new IllegalArgumentException("Dosis must not be null");
        }
        if(deworming.getVaccineCard() == null) {
            throw new IllegalArgumentException("Vaccine card must not be null");
        }
        return dewormingRepository.save(deworming);
    }
    @Transactional
    public List<DewormingEntity> getAllDewormings() {
        return dewormingRepository.findAll();
    }
    @Transactional
    public DewormingEntity getDewormingById(Long id) {
        return dewormingRepository.findById(id).orElse(null);
    }
    @Transactional
    public DewormingEntity updateDeworming(Long id, DewormingEntity deworming) {
        if (dewormingRepository.existsById(id)) {
            deworming.setId(id);
            return dewormingRepository.save(deworming);
        } else {
            return null;
        }
    }
    @Transactional
    public void deleteDeworming(Long id) {
        dewormingRepository.deleteById(id);
    }
}
