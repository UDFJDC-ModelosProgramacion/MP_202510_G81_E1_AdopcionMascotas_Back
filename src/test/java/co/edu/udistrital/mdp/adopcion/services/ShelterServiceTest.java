package co.edu.udistrital.mdp.adopcion.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.udistrital.mdp.adopcion.entities.ShelterEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.ShelterEventEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import co.edu.udistrital.mdp.adopcion.services.ShelterService;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(ShelterService.class)
public class ShelterServiceTest {
    @Autowired
    private ShelterService shelterService;
    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();
    private List<ShelterEntity> shelterList;
    private List<ShelterEventEntity> shelterEventList;
    private List<PetEntity> petList;

@BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from ShelterEntity");
        entityManager.getEntityManager().createQuery("delete from ShelterEventEntity");
        entityManager.getEntityManager().createQuery("delete from PetEntity");
    }

    private void insertData() {
        int n=5;
        for (int i = 0; i < n; i++) {
            ShelterEntity shelter = factory.manufacturePojo(ShelterEntity.class);
            entityManager.persist(shelter);
            shelterList.add(shelter);

            ShelterEventEntity shelterEvent = factory.manufacturePojo(ShelterEventEntity.class);
            entityManager.persist(shelterEvent);
            shelterEventList.add(shelterEvent);

            PetEntity pet = factory.manufacturePojo(PetEntity.class);
            entityManager.persist(pet);
            petList.add(pet);
        }
        
        
}
    /**
     * Test for createShelter method
     */
    @Test
    public void testCreateShelter() {
        ShelterEntity shelter = factory.manufacturePojo(ShelterEntity.class);
        ShelterEntity createdShelter = shelterService.createShelter(shelter);
        assertNotNull(createdShelter);
        assertEquals(shelter.getName(), createdShelter.getName());
        assertEquals(shelter.getAddress(), createdShelter.getAddress());
        assertEquals(shelter.getPhone(), createdShelter.getPhone());
        assertEquals(shelter.getEmail(), createdShelter.getEmail());
        
    }
    /**
     * Test for getAllShelters method
     */
    @Test
    public void testGetAllShelters() {
        List<ShelterEntity> shelters = shelterService.getAllShelters();
        assertEquals(shelterList.size(), shelters.size());
    }
    /**
     * Test for getShelterById method
     */
    @Test
    public void testGetShelterById() {
        ShelterEntity shelter = shelterList.get(0);
        ShelterEntity foundShelter = shelterService.getShelterById(shelter.getId());
        assertNotNull(foundShelter);
        assertEquals(shelter.getName(), foundShelter.getName());
        assertEquals(shelter.getAddress(), foundShelter.getAddress());
        assertEquals(shelter.getPhone(), foundShelter.getPhone());
        assertEquals(shelter.getEmail(), foundShelter.getEmail());
    }
    /**
     * Test for updateShelter method
     */
    @Test
    public void testUpdateShelter() {
        ShelterEntity shelter = shelterList.get(0);
        ShelterEntity updatedShelter = factory.manufacturePojo(ShelterEntity.class);
        updatedShelter.setId(shelter.getId());
        ShelterEntity result = shelterService.updateShelter(shelter.getId(), updatedShelter);
        assertNotNull(result);
        assertEquals(updatedShelter.getName(), result.getName());
        assertEquals(updatedShelter.getAddress(), result.getAddress());
        assertEquals(updatedShelter.getPhone(), result.getPhone());
        assertEquals(updatedShelter.getEmail(), result.getEmail());
    }
    /**
     * Test for deleteShelter method
     */
    @Test
    public void testDeleteShelter() {
        ShelterEntity shelter = shelterList.get(0);
        shelterService.deleteShelter(shelter.getId());
        ShelterEntity deletedShelter = entityManager.find(ShelterEntity.class, shelter.getId());
        assertNull(deletedShelter);
    }
    /**
     * Test for deleteShelter method with non-existing id
     */
    @Test
    public void testDeleteShelterWithNonExistingId() {
        ShelterEntity shelter = factory.manufacturePojo(ShelterEntity.class);
        shelter.setId(999L);
        shelterService.deleteShelter(shelter.getId());
        ShelterEntity deletedShelter = entityManager.find(ShelterEntity.class, shelter.getId());
        assertNull(deletedShelter);
    }
    /**
     * Test for updateShelter method with non-existing id
     */
    @Test
    public void testUpdateShelterWithNonExistingId() {
        ShelterEntity shelter = factory.manufacturePojo(ShelterEntity.class);
        shelter.setId(999L);
        ShelterEntity updatedShelter = factory.manufacturePojo(ShelterEntity.class);
        updatedShelter.setId(shelter.getId());
        ShelterEntity result = shelterService.updateShelter(shelter.getId(), updatedShelter);
        assertNull(result);
    }
    /**
     * Test for createShelter method with null name
     */
    @Test
    public void testCreateShelterWithNullName() {
        ShelterEntity shelter = factory.manufacturePojo(ShelterEntity.class);
        shelter.setName(null);
        ShelterEntity createdShelter = shelterService.createShelter(shelter);
        assertNull(createdShelter);
    }
    /**
     * Test for createShelter method with null address
     */
    @Test
    public void testCreateShelterWithNullAddress() {
        ShelterEntity shelter = factory.manufacturePojo(ShelterEntity.class);
        shelter.setAddress(null);
        ShelterEntity createdShelter = shelterService.createShelter(shelter);
        assertNull(createdShelter);
    }
    /**
     * Test for createShelter method with null phone
     */
    @Test
    public void testCreateShelterWithNullPhone() {
        ShelterEntity shelter = factory.manufacturePojo(ShelterEntity.class);
        shelter.setPhone(null);
        ShelterEntity createdShelter = shelterService.createShelter(shelter);
        assertNull(createdShelter);
    }
    /**
     * Test for createShelter method with null email
     */
    @Test
    public void testCreateShelterWithNullEmail() {
        ShelterEntity shelter = factory.manufacturePojo(ShelterEntity.class);
        shelter.setEmail(null);
        ShelterEntity createdShelter = shelterService.createShelter(shelter);
        assertNull(createdShelter);
    }
}
