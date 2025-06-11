package co.edu.udistrital.mdp.adopcion.services.adoption;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionApplicationEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(AdoptionService.class)
public class AdoptionServiceTest {

    @Autowired
    private AdoptionService adoptionService;

    @Autowired
    private TestEntityManager entityManager;
    
    private PodamFactory factory = new PodamFactoryImpl();
    private List<AdoptionEntity> adoptionList = new ArrayList<>();
    private List<AdoptionApplicationEntity> adoptionApplicationList = new ArrayList<>();
    private List<OwnerEntity> ownerList = new ArrayList<>();
    private List<PetEntity> petList = new ArrayList<>();
    private List<VeterinarianEntity> veterinarianList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }
    private void clearData() {
        entityManager.getEntityManager().createQuery("DELETE FROM AdoptionEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("DELETE FROM AdoptionAplicationEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("DELETE FROM OwnerEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("DELETE FROM PetEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("DELETE FROM VeterinarianEntity").executeUpdate();
    }
    private void insertData() {
        int n = 5;
        for (int i = 0; i < n; i++) {
            OwnerEntity owner = factory.manufacturePojo(OwnerEntity.class);
            entityManager.persist(owner);
            ownerList.add(owner);

            PetEntity pet = factory.manufacturePojo(PetEntity.class);
            entityManager.persist(pet);
            petList.add(pet);

            VeterinarianEntity veterinarian = factory.manufacturePojo(VeterinarianEntity.class);
            entityManager.persist(veterinarian);
            veterinarianList.add(veterinarian);

            AdoptionApplicationEntity adoptionApplication = factory.manufacturePojo(AdoptionApplicationEntity.class);
            entityManager.persist(adoptionApplication);
            adoptionApplicationList.add(adoptionApplication);
            
            AdoptionEntity adoption = factory.manufacturePojo(AdoptionEntity.class);
            adoption.setOwner(owner);
            adoption.setPet(pet);
            adoption.setVeterinarian(veterinarian);
            adoption.setAdoptionApplication(adoptionApplication);
            entityManager.persist(adoption);
            adoptionList.add(adoption);

        }
    }
    /**
     * Test for createAdoption method
     */
    @Test
    void testCreateAdoption() {
        
        
        PetEntity newPet = factory.manufacturePojo(PetEntity.class);
        entityManager.persist(newPet);
        
        AdoptionEntity newAdoption = factory.manufacturePojo(AdoptionEntity.class);
        newAdoption.setOwner(ownerList.get(0));
        newAdoption.setPet(newPet);

        
        
        VeterinarianEntity newVeterinarian = factory.manufacturePojo(VeterinarianEntity.class);
        entityManager.persist(newVeterinarian);
        newAdoption.setVeterinarian(newVeterinarian);
        
    
        
        AdoptionApplicationEntity newApplication = factory.manufacturePojo(AdoptionApplicationEntity.class);
        entityManager.persist(newApplication);
        newAdoption.setAdoptionApplication(newApplication);

        AdoptionEntity createdAdoption = adoptionService.createAdoption(newAdoption);

        assertNotNull(createdAdoption);
        assertNotNull(createdAdoption.getId());

        assertEquals(newAdoption.getOwner().getId(), createdAdoption.getOwner().getId());
        assertEquals(newAdoption.getPet().getId(), createdAdoption.getPet().getId());
        assertEquals(newAdoption.getVeterinarian().getId(), createdAdoption.getVeterinarian().getId());
        assertEquals(newAdoption.getAdoptionApplication().getId(), createdAdoption.getAdoptionApplication().getId());
    }
    /**
          * Test for getAllAdoptions method
     */
    @Test
    void testGetAllAdoptions() {
        List<AdoptionEntity> adoptions = adoptionService.getAllAdoptions();
        assertEquals(adoptionList.size(), adoptions.size());
    }
    /**
     * Test for getAdoptionById method
     */
    @Test
    void testGetAdoptionById() {
        AdoptionEntity adoption = adoptionList.get(0);
        AdoptionEntity foundAdoption = adoptionService.getAdoptionById(adoption.getId());
        assertNotNull(foundAdoption);
        assertEquals(adoption.getId(), foundAdoption.getId());
        assertEquals(adoption.getOwner().getId(), foundAdoption.getOwner().getId());
        assertEquals(adoption.getPet().getId(), foundAdoption.getPet().getId());
        assertEquals(adoption.getVeterinarian().getId(), foundAdoption.getVeterinarian().getId());
        assertEquals(adoption.getAdoptionApplication().getId(), foundAdoption.getAdoptionApplication().getId());
    }
    /**
     * Test for updateAdoption method
     */
    @Test
    void testUpdateAdoption() {
        AdoptionEntity adoption = adoptionList.get(0);
        AdoptionEntity updatedAdoption = factory.manufacturePojo(AdoptionEntity.class);
        updatedAdoption.setId(adoption.getId());
        updatedAdoption.setOwner(ownerList.get(1));
        updatedAdoption.setPet(petList.get(1));
        updatedAdoption.setVeterinarian(veterinarianList.get(1));
        updatedAdoption.setAdoptionApplication(adoptionApplicationList.get(1));
        AdoptionEntity result = adoptionService.updateAdoption(adoption.getId(), updatedAdoption);
        assertNotNull(result);
        assertEquals(updatedAdoption.getId(), result.getId());
        assertEquals(updatedAdoption.getOwner().getId(), result.getOwner().getId());
        assertEquals(updatedAdoption.getPet().getId(), result.getPet().getId());
        assertEquals(updatedAdoption.getVeterinarian().getId(), result.getVeterinarian().getId());
        assertEquals(updatedAdoption.getAdoptionApplication().getId(), result.getAdoptionApplication().getId());
    }
    /**
     * Test for deleteAdoption method
     */
    @Test
    void testDeleteAdoption() {
        AdoptionEntity adoption = adoptionList.get(0);
        adoptionService.deleteAdoption(adoption.getId());
        entityManager.flush(); 
        AdoptionEntity deletedAdoption = entityManager.find(AdoptionEntity.class, adoption.getId());
        assertNull(deletedAdoption);
    }

        
}