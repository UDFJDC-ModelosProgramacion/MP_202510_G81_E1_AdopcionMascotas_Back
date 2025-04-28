package co.edu.udistrital.mdp.adopcion.services.pet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;


import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionAplicationEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionFollowUpEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionTestEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.DisponibilityEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.ShelterArrivalEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.ShelterEventEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import co.edu.udistrital.mdp.adopcion.services.person.OwnerService;
import co.edu.udistrital.mdp.adopcion.services.person.VeterinarianService;


import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@DataJpaTest
@Transactional
@Import(PetServices.class)
public class PetServicesTest {
    @Autowired
    private PetServices petService;
    @Autowired
    private TestEntityManager entityManager;
    private PodamFactory factory = new PodamFactoryImpl();
    private List<PetEntity> petList = new ArrayList<>();
    private List<ShelterArrivalEntity> shelterArrivalList = new ArrayList<>();
    private List<ShelterEventEntity> shelterEventList = new ArrayList<>();
    private List<DisponibilityEntity> disponibilityList = new ArrayList<>();
    private List<AdoptionAplicationEntity> adoptionApplicationList = new ArrayList<>();
    private List<AdoptionFollowUpEntity> adoptionFollowUpList = new ArrayList<>();
    private List<AdoptionTestEntity> adoptionTestList = new ArrayList<>();
    private List<AdoptionEntity> adoptionList = new ArrayList<>();


    @BeforeEach

    void setUp() {
        clearData();
        insertData();
    }
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from PetEntity");
        entityManager.getEntityManager().createQuery("delete from ShelterArrivalEntity");
        entityManager.getEntityManager().createQuery("delete from ShelterEventEntity");
        entityManager.getEntityManager().createQuery("delete from DisponibilityEntity");
        entityManager.getEntityManager().createQuery("delete from AdoptionAplicationEntity");
        entityManager.getEntityManager().createQuery("delete from AdoptionFollowUpEntity");
        entityManager.getEntityManager().createQuery("delete from AdoptionTestEntity");
        entityManager.getEntityManager().createQuery("delete from AdoptionEntity");

    }
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PetEntity pet = factory.manufacturePojo(PetEntity.class);
            entityManager.persist(pet);
            petList.add(pet);
        }
        for (int i = 0; i < 3; i++) {
            ShelterArrivalEntity shelterArrival = factory.manufacturePojo(ShelterArrivalEntity.class);
            entityManager.persist(shelterArrival);
            shelterArrivalList.add(shelterArrival);
        }
        for (int i = 0; i < 3; i++) {
            ShelterEventEntity shelterEvent = factory.manufacturePojo(ShelterEventEntity.class);
            entityManager.persist(shelterEvent);
            shelterEventList.add(shelterEvent);
        }
        for (int i = 0; i < 3; i++) {
            DisponibilityEntity disponibility = factory.manufacturePojo(DisponibilityEntity.class);
            entityManager.persist(disponibility);
            disponibilityList.add(disponibility);
        }
        for (int i = 0; i < 3; i++) {
            AdoptionAplicationEntity adoptionApplication = factory.manufacturePojo(AdoptionAplicationEntity.class);
            entityManager.persist(adoptionApplication);
            adoptionApplicationList.add(adoptionApplication);
        }
        for (int i = 0; i < 3; i++) {
            AdoptionFollowUpEntity adoptionFollowUp = factory.manufacturePojo(AdoptionFollowUpEntity.class);
            entityManager.persist(adoptionFollowUp);
            adoptionFollowUpList.add(adoptionFollowUp);
        }
        for (int i = 0; i < 3; i++) {
            AdoptionTestEntity adoptionTest = factory.manufacturePojo(AdoptionTestEntity.class);
            entityManager.persist(adoptionTest);
            adoptionTestList.add(adoptionTest);
        }
        for (int i = 0; i < 3; i++) {
            AdoptionEntity adoption = factory.manufacturePojo(AdoptionEntity.class);
            entityManager.persist(adoption);
            adoptionList.add(adoption);
        }

    }
    @Test
    void testCreatePet() {
        PetEntity pet = factory.manufacturePojo(PetEntity.class);
        PetEntity createdPet = petService.createPet(pet);
        assertNotNull(createdPet.getId());
        assertEquals(pet.getName(), createdPet.getName());
        assertEquals(pet.getBirthDate(), createdPet.getBirthDate());
        assertEquals(pet.getVacineCard(), createdPet.getVacineCard());
    }
    @Test
    void testCreatePetWithEmptyName() {
        PetEntity pet = factory.manufacturePojo(PetEntity.class);
        pet.setName(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            petService.createPet(pet);
        });
        String expectedMessage = "The name of the pet must not be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testCreatePetWithEmptyBirthDate() {
        PetEntity pet = factory.manufacturePojo(PetEntity.class);
        pet.setBirthDate(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            petService.createPet(pet);
        });
        String expectedMessage = "The birth date of the pet must not be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testCreatePetWithEmptyVacineCard() {
        PetEntity pet = factory.manufacturePojo(PetEntity.class);
        pet.setVacineCard(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            petService.createPet(pet);
        });
        String expectedMessage = "The vacine card of the pet must not be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testUpdatePet() {
        PetEntity pet = petList.get(0);
        PetEntity updatedPet = factory.manufacturePojo(PetEntity.class);
        updatedPet.setId(pet.getId());
        PetEntity result = petService.updatePet(pet.getId(), updatedPet);
        assertNotNull(result);
        assertEquals(updatedPet.getName(), result.getName());
        assertEquals(updatedPet.getBirthDate(), result.getBirthDate());
        assertEquals(updatedPet.getVacineCard(), result.getVacineCard());
    }
    @Test
    void testUpdatePetWithNullName() {
        PetEntity pet = petList.get(0);
        PetEntity updatedPet = factory.manufacturePojo(PetEntity.class);
        updatedPet.setId(pet.getId());
        updatedPet.setName(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            petService.updatePet(pet.getId(), updatedPet);
        });
        String expectedMessage = "The name of the pet must not be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testUpdatePetWithNullBirthDate() {
        PetEntity pet = petList.get(0);
        PetEntity updatedPet = factory.manufacturePojo(PetEntity.class);
        updatedPet.setId(pet.getId());
        updatedPet.setBirthDate(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            petService.updatePet(pet.getId(), updatedPet);
        });
        String expectedMessage = "The birth date of the pet must not be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testUpdatePetWithNullVacineCard() {
        PetEntity pet = petList.get(0);
        PetEntity updatedPet = factory.manufacturePojo(PetEntity.class);
        updatedPet.setId(pet.getId());
        updatedPet.setVacineCard(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            petService.updatePet(pet.getId(), updatedPet);
        });
        String expectedMessage = "The vacine card of the pet must not be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void testUpdatePetWithAdmin() {
        PetEntity pet = petList.get(0);
        PetEntity updatedPet = factory.manufacturePojo(PetEntity.class);
        updatedPet.setId(pet.getId());
        updatedPet.setBirthDate(null); // Cambiar la fecha de nacimiento a null
        PetEntity result = petService.updatePet(pet.getId(), updatedPet, true); // Llamar al método con isAdmin = true
        assertNotNull(result);
        assertEquals(updatedPet.getName(), result.getName());
        assertEquals(updatedPet.getBirthDate(), result.getBirthDate());
        assertEquals(updatedPet.getVacineCard(), result.getVacineCard());
    }
}
