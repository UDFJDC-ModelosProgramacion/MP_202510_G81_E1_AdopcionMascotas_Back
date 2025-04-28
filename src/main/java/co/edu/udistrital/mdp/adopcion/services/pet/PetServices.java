package co.edu.udistrital.mdp.adopcion.services.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionAplicationRepository;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionFollowUpRepository;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionRepository;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionTestRepository;
import co.edu.udistrital.mdp.adopcion.repositories.event.MedicalEventRepository;
import co.edu.udistrital.mdp.adopcion.repositories.pet.PetRepository;
import co.edu.udistrital.mdp.adopcion.repositories.person.DisponibilityRepository;
import co.edu.udistrital.mdp.adopcion.repositories.person.VeterinarianRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PetServices {
    @Autowired
    private PetRepository petRepository;

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
    @Autowired
    private VeterinarianRepository veterinarianRepository;

    @Transactional
    public PetEntity createPet(PetEntity pet) {
        if (pet.getName() == null || pet.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("The name of the pet must not be empty");
        }
        if (pet.getBirthDate() == null) {
            throw new IllegalArgumentException("The birth date of the pet must not be empty");
        }
        if (pet.getVacineCard() == null) {
            throw new IllegalArgumentException("The vacine card of the pet must not be empty");
        }
        pet.setOwner(null);
        
        return petRepository.save(pet);
    }

    @Transactional
    public PetEntity updatePet(Long id, PetEntity pet) {
        PetEntity existingPet = petRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pet not found"));
        if (pet.getName() != null && !pet.getName().trim().isEmpty()) {
            existingPet.setName(pet.getName());
        }
        if (pet.getBirthDate() != null) {
            existingPet.setBirthDate(pet.getBirthDate());
        }
        if (pet.getVacineCard() != null) {
            existingPet.setVacineCard(pet.getVacineCard());
        }
        return petRepository.save(existingPet);
    }
    @Transactional
    public PetEntity updatePet(Long id, PetEntity pet, boolean isAdmin) {
        PetEntity existingPet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pet not found"));
        if (!isAdmin) {
            if (pet.getBirthDate() != null && !pet.getBirthDate().equals(existingPet.getBirthDate())) {
                throw new SecurityException("You do not have permission to change the birth date");
            }
        }
        if (pet.getName() != null && !pet.getName().trim().isEmpty()) {
            existingPet.setName(pet.getName());
        }
        if (isAdmin) {
            if (pet.getBirthDate() != null) {
                existingPet.setBirthDate(pet.getBirthDate());
            }
        }
        if (pet.getVacineCard() != null) {
            existingPet.setVacineCard(pet.getVacineCard());
        }
        return petRepository.save(existingPet);
    }
}