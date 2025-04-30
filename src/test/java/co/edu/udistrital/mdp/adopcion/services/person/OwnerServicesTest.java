package co.edu.udistrital.mdp.adopcion.services.person;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;


import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionAplicationEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionFollowUpEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionTestEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.DisponibilityEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.ShelterArrivalEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.ShelterEventEntity;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;




@DataJpaTest
@Transactional
@Import(OwnerService.class)
public class OwnerServicesTest {
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private TestEntityManager entityManager;
    private PodamFactory factory = new PodamFactoryImpl();
    private List<OwnerEntity> ownerList = new ArrayList<>();
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
        entityManager.getEntityManager().createQuery("delete from OwnerEntity");
        entityManager.getEntityManager().createQuery("delete from ShelterArrivalEntity");
        entityManager.getEntityManager().createQuery("delete from ShelterEventEntity");
        entityManager.getEntityManager().createQuery("delete from DisponibilityEntity");
        entityManager.getEntityManager().createQuery("delete from AdoptionAplicationEntity");
        entityManager.getEntityManager().createQuery("delete from AdoptionFollowUpEntity");
        entityManager.getEntityManager().createQuery("delete from AdoptionTestEntity");
        entityManager.getEntityManager().createQuery("delete from AdoptionEntity");

    }
    private void insertData() {
        int n = 5;
        for (int i = 0; i < n; i++) {
            OwnerEntity owner = factory.manufacturePojo(OwnerEntity.class);
            entityManager.persist(owner);
            ownerList.add(owner);
        }
        for (int i = 0; i < n; i++) {
            ShelterArrivalEntity shelterArrival = factory.manufacturePojo(ShelterArrivalEntity.class);
            entityManager.persist(shelterArrival);
            shelterArrivalList.add(shelterArrival);
        }
        for (int i = 0; i < n; i++) {
            ShelterEventEntity shelterEvent = factory.manufacturePojo(ShelterEventEntity.class);
            entityManager.persist(shelterEvent);
            shelterEventList.add(shelterEvent);
        }
        for (int i = 0; i < n; i++) {
            DisponibilityEntity disponibility = factory.manufacturePojo(DisponibilityEntity.class);
            entityManager.persist(disponibility);
            disponibilityList.add(disponibility);
        }
        for (int i = 0; i < n; i++) {
            AdoptionAplicationEntity adoptionApplication = factory.manufacturePojo(AdoptionAplicationEntity.class);
            entityManager.persist(adoptionApplication);
            adoptionApplicationList.add(adoptionApplication);
        }
        for (int i = 0; i < n; i++) {
            AdoptionFollowUpEntity adoptionFollowUp = factory.manufacturePojo(AdoptionFollowUpEntity.class);
            entityManager.persist(adoptionFollowUp);
            adoptionFollowUpList.add(adoptionFollowUp);
        }
        for (int i = 0; i < n; i++) {
            AdoptionTestEntity adoptionTest = factory.manufacturePojo(AdoptionTestEntity.class);
            entityManager.persist(adoptionTest);
            adoptionTestList.add(adoptionTest);
        }
        for (int i = 0; i < n; i++) {
            AdoptionEntity adoption = factory.manufacturePojo(AdoptionEntity.class);
            entityManager.persist(adoption);
            adoptionList.add(adoption);

        }

    }
    @Test
    void testCreateOwner() {
        OwnerEntity newOwner = factory.manufacturePojo(OwnerEntity.class);
        OwnerEntity createdOwner = ownerService.createOwner(newOwner);
        assertNotNull(createdOwner);
        OwnerEntity foundOwner = entityManager.find(OwnerEntity.class, createdOwner.getId());
        assertEquals(newOwner.getFirstName(), foundOwner.getFirstName());
        assertEquals(newOwner.getLastName(), foundOwner.getLastName());
        assertEquals(newOwner.getIdentificationNumber(), foundOwner.getIdentificationNumber());
        assertEquals(newOwner.getPhoneNumber(), foundOwner.getPhoneNumber());
        assertEquals(newOwner.getEmail(), foundOwner.getEmail());
    }
    @Test
    void testCreateOwnerWithNullName() {
        OwnerEntity
        newOwner = factory.manufacturePojo(OwnerEntity.class);
        newOwner.setFirstName(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ownerService.createOwner(newOwner);
        });
        String expectedMessage = "The name must not be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}

