package co.edu.udistrital.mdp.adopcion.services.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionAplicationRepository;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionFollowUpRepository;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionRepository;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionTestRepository;
import co.edu.udistrital.mdp.adopcion.repositories.event.MedicalEventRepository;
import co.edu.udistrital.mdp.adopcion.repositories.pet.PetRepository;
import co.edu.udistrital.mdp.adopcion.repositories.person.DisponibilityRepository;
import co.edu.udistrital.mdp.adopcion.repositories.person.VeterinarianRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PetService {
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
        if (pet.getVaccineCard() == null) {
            throw new IllegalArgumentException("The vacine card of the pet must not be empty");
        }
        pet.setOwners(null);
        
        return petRepository.save(pet);
    }

    @Transactional
    public PetEntity updatePet(Long id, PetEntity pet) {
        PetEntity existingPet = petRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pet not found"));
        if (pet.getName() != null && !pet.getName().trim().isEmpty()) {
            existingPet.setName(pet.getName());
        } else {
            throw new IllegalArgumentException("The name of the pet must not be empty");
        }
        if (pet.getBreed() != null && !pet.getBreed().trim().isEmpty()) {
            existingPet.setBreed(pet.getBreed());
        } else {
            throw new IllegalArgumentException("The breed of the pet must not be empty");
        }
        if (pet.getSize() != null) {
            existingPet.setSize(pet.getSize());
        } else {
            throw new IllegalArgumentException("The size of the pet must not be empty");
        }
        if (pet.getGender() != null) {
            existingPet.setGender(pet.getGender());
        } else {
            throw new IllegalArgumentException("The gender of the pet must not be empty");
        }
        if (pet.getBehaviorProfile() != null && !pet.getBehaviorProfile().trim().isEmpty()) {
            existingPet.setBehaviorProfile(pet.getBehaviorProfile());
        } else {
            throw new IllegalArgumentException("The behavior profile of the pet must not be empty");
        }
        if (pet.getBirthDate() != null) {
            existingPet.setBirthDate(pet.getBirthDate());
        } else {
            throw new IllegalArgumentException("The birth date of the pet must not be empty");
        }
        if (pet.getShelter() != null) {
            existingPet.setShelter(pet.getShelter());
        } else {
            throw new IllegalArgumentException("The shelter of the pet must not be empty");
        }
        if (pet.getShelterArrival() != null) {
            existingPet.setShelterArrival(pet.getShelterArrival());
        } else {
            throw new IllegalArgumentException("The shelter arrival of the pet must not be empty");
        }
        if (pet.getVaccineCard() != null) {
            existingPet.setVaccineCard(pet.getVaccineCard());
        } else {
            throw new IllegalArgumentException("The vaccine card of the pet must not be empty");
        }
        return petRepository.save(existingPet);
    }
    @Transactional
    public PetEntity updatePet(Long id, PetEntity pet, boolean isAdmin) {
        if (!isAdmin) {
            throw new SecurityException("You do not have permission to update pet information");
        }

        PetEntity existingPet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pet not found"));

        if (pet.getName() == null || pet.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("The name of the pet must not be empty");
        }
        existingPet.setName(pet.getName());

        if (pet.getBreed() == null || pet.getBreed().trim().isEmpty()) {
            throw new IllegalArgumentException("The breed of the pet must not be empty");
        }
        existingPet.setBreed(pet.getBreed());

        if (pet.getSize() == null) {
            throw new IllegalArgumentException("The size of the pet must not be empty");
        }
        existingPet.setSize(pet.getSize());

        if (pet.getGender() == null) {
            throw new IllegalArgumentException("The gender of the pet must not be empty");
        }
        existingPet.setGender(pet.getGender());

        if (pet.getBehaviorProfile() == null || pet.getBehaviorProfile().trim().isEmpty()) {
            throw new IllegalArgumentException("The behavior profile of the pet must not be empty");
        }
        existingPet.setBehaviorProfile(pet.getBehaviorProfile());

        if (pet.getBirthDate() == null) {
            throw new IllegalArgumentException("The birth date of the pet must not be empty");
        }
        existingPet.setBirthDate(pet.getBirthDate());

        if (pet.getShelter() == null) {
            throw new IllegalArgumentException("The shelter of the pet must not be empty");
        }
        existingPet.setShelter(pet.getShelter());

        if (pet.getShelterArrival() == null) {
            throw new IllegalArgumentException("The shelter arrival of the pet must not be empty");
        }
        existingPet.setShelterArrival(pet.getShelterArrival());

        if (pet.getVaccineCard() == null) {
            throw new IllegalArgumentException("The vaccine card of the pet must not be empty");
        }
        existingPet.setVaccineCard(pet.getVaccineCard());

        return petRepository.save(existingPet);
    }
}