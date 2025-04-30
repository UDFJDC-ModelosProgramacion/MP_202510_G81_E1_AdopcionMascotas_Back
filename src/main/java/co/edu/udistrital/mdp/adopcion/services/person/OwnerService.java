package co.edu.udistrital.mdp.adopcion.services.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionAplicationRepository;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionFollowUpRepository;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionRepository;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionTestRepository;
import co.edu.udistrital.mdp.adopcion.repositories.event.MedicalEventRepository;
import co.edu.udistrital.mdp.adopcion.repositories.person.DisponibilityRepository;
import co.edu.udistrital.mdp.adopcion.repositories.person.OwnerRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private MedicalEventRepository medicalEventRepository;
    @Autowired
    private DisponibilityRepository disponibilityRepository;
    @Autowired
    private AdoptionAplicationRepository adoptionApplicationRepository;
    @Autowired
    private AdoptionFollowUpRepository adoptionFollowUpRepository;
    @Autowired
    private AdoptionRepository adoptionRepository;
    @Autowired
    private AdoptionTestRepository adoptionTestRepository;

    @Transactional
    public OwnerEntity createOwner(OwnerEntity owner) {
        if (owner.getFirstName() == null || owner.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("The name must not be empty");
        }
        
        if (owner.getLastName() == null || owner.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("The last name must not be empty");
        }
        
        if (owner.getPhoneNumber() == null || owner.getPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("The phone number must not be empty");
        }
        
        if (owner.getEmail() == null || owner.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("The email must not be empty");
        }
        
        if (owner.getAddress() == null || owner.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("The address must not be empty");
        }
        
        if (owner.getHouseType() == null) {
            throw new IllegalArgumentException("The house type must not be null");
        }
        
        return ownerRepository.save(owner);
    }
    @Transactional
    public OwnerEntity updateOwner(Long id, OwnerEntity owner, boolean isAdmin) {
        if (!ownerRepository.existsById(id)) {
            return null;
        }
        
        OwnerEntity existingOwner = ownerRepository.findById(id).orElse(null);
        if (existingOwner == null) {
            return null;
        }
        
        if (owner.getIdentificationNumber() != null && 
            !owner.getIdentificationNumber().equals(existingOwner.getIdentificationNumber())) {
            
            if (!isAdmin) {
                throw new SecurityException("Only an admin can change the identification number");
            }
            
            if (ownerRepository.existsByIdentificationNumber(owner.getIdentificationNumber())) {
                throw new IllegalArgumentException("Your identification number already exists");
            }
        }
        

        owner.setId(id);
        owner.setAdoptions(existingOwner.getAdoptions());
        owner.setAdoptionTests(existingOwner.getAdoptionTests());
        owner.setAdoptionApplications(existingOwner.getAdoptionApplications());
        owner.setPets(existingOwner.getPets());
        
        return ownerRepository.save(owner);
    }
    @Transactional
    public void deleteOwner(Long id, boolean isAdmin) {
        if (!isAdmin) {
            throw new SecurityException("Only an admin can delete an owner");
        }
        if (!ownerRepository.existsById(id)) {
            throw new IllegalArgumentException("The owner with the given ID does not exist");
        }
        medicalEventRepository.deleteByOwnerId(id);
        disponibilityRepository.deleteByOwnerId(id);
        adoptionApplicationRepository.deleteByOwnerId(id);
        adoptionFollowUpRepository.deleteByOwnerId(id);
        adoptionRepository.deleteByOwnerId(id);
        adoptionTestRepository.deleteByOwnerId(id);
        
        ownerRepository.deleteById(id);
    }
}