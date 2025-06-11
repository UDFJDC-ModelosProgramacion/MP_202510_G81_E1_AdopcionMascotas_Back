package co.edu.udistrital.mdp.adopcion.services.adoption;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionAplicationEntity;
import co.edu.udistrital.mdp.adopcion.exceptions.EntityNotFoundException;
import co.edu.udistrital.mdp.adopcion.exceptions.IllegalOperationException;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionApplicationRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdoptionApplicationService {

    @Autowired
    private AdoptionApplicationRepository adoptionApplicationRepository;

    @Transactional
    public AdoptionAplicationEntity createApplication(AdoptionAplicationEntity application) throws IllegalOperationException {
        if (application.getApplicationDate() == null) {
            throw new IllegalOperationException("Application date must not be null");
        }
        if (application.getApplicationEnd() == null) {
            throw new IllegalOperationException("Application end date must not be null");
        }
        if (application.getApplicationStatus() == null) {
            throw new IllegalOperationException("Application status must not be null");
        }
        if (application.getResult() == null) {
            throw new IllegalOperationException("Application result must not be null");
        }
        if (application.getOwner() == null) {
            throw new IllegalOperationException("Owner must not be null");
        }
        if (application.getVeterinarian() == null) {
            throw new IllegalOperationException("Veterinarian must not be null");
        }
        if (application.getPet() == null) {
            throw new IllegalOperationException("Pet must not be null");
        }
        return adoptionApplicationRepository.save(application);
    }

    @Transactional(readOnly = true)
    public List<AdoptionAplicationEntity> getAllApplications() {
        return adoptionApplicationRepository.findAll();
    }

    @Transactional(readOnly = true)
    public AdoptionAplicationEntity getApplicationById(Long id) throws EntityNotFoundException {
        Optional<AdoptionAplicationEntity> application = adoptionApplicationRepository.findById(id);
        if (application.isEmpty()) {
            throw new EntityNotFoundException("Adoption Application with id " + id + " not found");
        }
        return application.get();
    }

    @Transactional
    public AdoptionAplicationEntity updateApplication(Long id, AdoptionAplicationEntity application) throws EntityNotFoundException {
        if (!adoptionApplicationRepository.existsById(id)) {
            throw new EntityNotFoundException("Adoption Application with id " + id + " not found");
        }
        application.setId(id);
        return adoptionApplicationRepository.save(application);
    }

    @Transactional
    public void deleteApplication(Long id) throws EntityNotFoundException {
        if (!adoptionApplicationRepository.existsById(id)) {
            throw new EntityNotFoundException("Adoption Application with id " + id + " not found");
        }
        adoptionApplicationRepository.deleteById(id);
    }
}
