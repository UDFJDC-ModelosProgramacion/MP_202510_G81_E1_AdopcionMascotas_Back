package co.edu.udistrital.mdp.adopcion.services.person;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionAplicationEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionTestEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;




@DataJpaTest
@Transactional
@Import(OwnerService.class)
public class OwnerServiceTest {
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private TestEntityManager entityManager;
    private PodamFactory factory = new PodamFactoryImpl();
    private List<OwnerEntity> ownerList = new ArrayList<>();   
    private List<PetEntity> petList = new ArrayList<>();
    private List<AdoptionAplicationEntity> adoptionApplicationList = new ArrayList<>();
    private List<AdoptionTestEntity> adoptionTestList = new ArrayList<>();
    private List<AdoptionEntity> adoptionList = new ArrayList<>();


    @BeforeEach

    void setUp() {
        clearData();
        insertData();
    }
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from OwnerEntity");
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
        int n = 5;
        for (int i = 0; i < n; i++) {
            AdoptionEntity adoption = factory.manufacturePojo(AdoptionEntity.class);
            entityManager.persist(adoption);
            adoptionList.add(adoption);

            AdoptionTestEntity adoptionTest = factory.manufacturePojo(AdoptionTestEntity.class);
            entityManager.persist(adoptionTest);
            adoptionTestList.add(adoptionTest);

            AdoptionAplicationEntity adoptionApplication = factory.manufacturePojo(AdoptionAplicationEntity.class);
            entityManager.persist(adoptionApplication);
            adoptionApplicationList.add(adoptionApplication);

            PetEntity pet = factory.manufacturePojo(PetEntity.class);
            pet.setAdoption(adoption);
            entityManager.persist(pet);
            petList.add(pet);

            OwnerEntity owner = factory.manufacturePojo(OwnerEntity.class);
            entityManager.persist(owner);
            ownerList.add(owner);

            owner.getAdoptions().add(adoption);
            owner.getAdoptionApplications().add(adoptionApplication);
            owner.getAdoptionTests().add(adoptionTest);
            owner.getPets().add(pet);
            
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
        assertEquals(newOwner.getPhoneNumber(), foundOwner.getPhoneNumber());
        assertEquals(newOwner.getEmail(), foundOwner.getEmail());
        assertEquals(newOwner.getAddress(), foundOwner.getAddress());
        assertEquals(newOwner.getHouseType(), foundOwner.getHouseType());
        assertEquals(newOwner.getPets(), foundOwner.getPets());
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

    @Test
    void testgetOwner() {
        OwnerEntity owner = ownerList.get(0);
        OwnerEntity foundOwner = ownerService.getOwner(owner.getId());
        assertNotNull(foundOwner);
        assertEquals(owner.getFirstName(), foundOwner.getFirstName());
        assertEquals(owner.getLastName(), foundOwner.getLastName());
        assertEquals(owner.getPhoneNumber(), foundOwner.getPhoneNumber());
        assertEquals(owner.getEmail(), foundOwner.getEmail());
        assertEquals(owner.getAddress(), foundOwner.getAddress());
        assertEquals(owner.getHouseType(), foundOwner.getHouseType());
    }

    @Test
    void testgetOwners() {
        List<OwnerEntity> foundOwners = ownerService.getOwners();
        assertNotNull(foundOwners);
        assertEquals(ownerList.size(), foundOwners.size());
        for (int i = 0; i < ownerList.size(); i++) {
            OwnerEntity expectedOwner = ownerList.get(i);
            OwnerEntity actualOwner = foundOwners.get(i);
            assertEquals(expectedOwner.getFirstName(), actualOwner.getFirstName());
            assertEquals(expectedOwner.getLastName(), actualOwner.getLastName());
            assertEquals(expectedOwner.getPhoneNumber(), actualOwner.getPhoneNumber());
            assertEquals(expectedOwner.getEmail(), actualOwner.getEmail());
            assertEquals(expectedOwner.getAddress(), actualOwner.getAddress());
            assertEquals(expectedOwner.getHouseType(), actualOwner.getHouseType());
        }
    }
}

