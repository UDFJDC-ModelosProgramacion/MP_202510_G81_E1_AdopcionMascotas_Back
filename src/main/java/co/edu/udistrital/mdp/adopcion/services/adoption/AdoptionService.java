package co.edu.udistrital.mdp.adopcion.services.adoption;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionEntity;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionApplicationRepository;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionRepository;
import co.edu.udistrital.mdp.adopcion.repositories.person.OwnerRepository;
import co.edu.udistrital.mdp.adopcion.repositories.person.VeterinarianRepository;
import co.edu.udistrital.mdp.adopcion.repositories.pet.PetRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdoptionService {
    @Autowired
    private AdoptionRepository adoptionRepository;
    @Autowired
    private AdoptionApplicationRepository adoptionAplicationRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private VeterinarianRepository veterinarianRepository;
    @Autowired
    private PetRepository petRepository;
    
    @Transactional
    public AdoptionEntity createAdoption(AdoptionEntity adoption) {
        if (adoption.getDescription() == null) {
            throw new IllegalArgumentException("Description must not be null");
        }
        if (adoption.getObservations() == null) {
            throw new IllegalArgumentException("Observations must not be null");
        }
        if (adoption.getOwner() == null) {
            throw new IllegalArgumentException("Owner must not be null");
        }
        if (adoption.getVeterinarian() == null) {
            throw new IllegalArgumentException("Veterinarian must not be null");
        }
        if (adoption.getPet() == null) {
            throw new IllegalArgumentException("Pet must not be null");
        }
        if (adoption.getAdoptionApplication() == null) {
            throw new IllegalArgumentException("Adoption application must not be null");
        }
        if (adoption.getAdoptionStatus() == null) {
            throw new IllegalArgumentException("Adoption status must not be null");
        }
        return adoptionRepository.save(adoption);
    }
    @Transactional
    public List<AdoptionEntity> getAllAdoptions() {
        return adoptionRepository.findAll();
    }
    @Transactional
    public AdoptionEntity getAdoptionById(Long id) {
        return adoptionRepository.findById(id).orElse(null);
    }
    @Transactional
    public AdoptionEntity updateAdoption(Long id, AdoptionEntity adoption) {
        if (adoptionRepository.existsById(id)) {
            return adoptionRepository.save(adoption);
        } else {
            throw new IllegalArgumentException("Adoption with id " + id + " does not exist");
        }
    }
    @Transactional
    public void deleteAdoption(Long id) {
        if (adoptionRepository.existsById(id)) {
            adoptionRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Adoption with id " + id + " does not exist");
        }
    }

}