package co.edu.udistrital.mdp.adopcion.services.adoption;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionAplicationEntity;
import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.Speciality;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import jakarta.transaction.Transactional;
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
    private List<AdoptionEntity> adoptionList;
    private List<AdoptionAplicationEntity> adoptionApplicationList;
    private List<OwnerEntity> ownerList;
    private List<PetEntity> petList;
    private List<VeterinarianEntity> veterinarianList;

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from AdoptionEntity");
        entityManager.getEntityManager().createQuery("delete from AdoptionAplicationEntity");
        entityManager.getEntityManager().createQuery("delete from OwnerEntity");
        entityManager.getEntityManager().createQuery("delete from PetEntity");
        entityManager.getEntityManager().createQuery("delete from VeterinarianEntity");
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

            AdoptionEntity adoption = factory.manufacturePojo(AdoptionEntity.class);
            entityManager.persist(adoption);
            adoptionList.add(adoption);

            AdoptionAplicationEntity adoptionApplication = factory.manufacturePojo(AdoptionAplicationEntity.class);
            entityManager.persist(adoptionApplication);
            adoptionApplicationList.add(adoptionApplication);
        }
    }
    /**
     * Test for createAdoption method
     */
    @Test
    void testCreateAdoption() {
        AdoptionEntity adoption= factory.manufacturePojo(AdoptionEntity.class);
        adoption.setOwner(ownerList.get(0));
        adoption.setPet(petList.get(0));
        adoption.setVeterinarian(veterinarianList.get(0));
        adoption.setAdoptionApplication(adoptionApplicationList.get(0));
        AdoptionEntity createdAdoption = adoptionService.createAdoption(adoption);
        assertNotNull(createdAdoption);
        assertNotNull(createdAdoption.getId());
        assertEquals(adoption.getOwner().getId(), createdAdoption.getOwner().getId());
        assertEquals(adoption.getPet().getId(), createdAdoption.getPet().getId());
        assertEquals(adoption.getVeterinarian().getId(), createdAdoption.getVeterinarian().getId());
        assertEquals(adoption.getAdoptionApplication().getId(), createdAdoption.getAdoptionApplication().getId());
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
        AdoptionEntity deletedAdoption = entityManager.find(AdoptionEntity.class, adoption.getId());
        assertNull(deletedAdoption);
    }

        
}
