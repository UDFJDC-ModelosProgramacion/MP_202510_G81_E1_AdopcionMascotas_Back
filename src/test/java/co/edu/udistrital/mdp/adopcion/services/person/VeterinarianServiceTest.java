package co.edu.udistrital.mdp.adopcion.services.person;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import co.edu.udistrital.mdp.adopcion.entities.events.MedicalEventEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.DisponibilityEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.Speciality;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@DataJpaTest
@Transactional
@Import(VeterinarianService.class)
public class VeterinarianServiceTest {
    @Autowired
    private VeterinarianService veterinarianService;
    @Autowired
    private TestEntityManager entityManager;
    private PodamFactory factory = new PodamFactoryImpl();
    private List<VeterinarianEntity> veterinarianList = new ArrayList<>();
    private List<MedicalEventEntity> medicalEventList = new ArrayList<>();
    private List<DisponibilityEntity> disponibilityList = new ArrayList<>();
    private List<AdoptionAplicationEntity> adoptionApplicationList = new ArrayList<>();
    private List<AdoptionFollowUpEntity> adoptionFollowUpList = new ArrayList<>();
    private List<AdoptionEntity> adoptionList = new ArrayList<>();
    private List<PetEntity> petList = new ArrayList<>();
    
    
    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from VeterinarianEntity");
        entityManager.getEntityManager().createQuery("delete from MedicalEventEntity");
        entityManager.getEntityManager().createQuery("delete from DisponibilityEntity");
        entityManager.getEntityManager().createQuery("delete from AdoptionAplicationEntity");
        entityManager.getEntityManager().createQuery("delete from AdoptionFollowUpEntity");
        entityManager.getEntityManager().createQuery("delete from AdoptionTestEntity");
        entityManager.getEntityManager().createQuery("delete from AdoptionEntity");
        entityManager.getEntityManager().createQuery("delete from PetEntity");
    }
    
    private void insertData() {
        int n = 5;
        for (int i = 0; i < n; i++) {
            PetEntity pet = factory.manufacturePojo(PetEntity.class);
            entityManager.persist(pet);
            petList.add(pet);

            MedicalEventEntity medicalEvent = factory.manufacturePojo(MedicalEventEntity.class);
            entityManager.persist(medicalEvent);
            medicalEvent.setPet(pet);
            medicalEventList.add(medicalEvent);

            DisponibilityEntity disponibility = factory.manufacturePojo(DisponibilityEntity.class);
            entityManager.persist(disponibility);
            disponibilityList.add(disponibility);

            AdoptionAplicationEntity adoptionApplication = factory.manufacturePojo(AdoptionAplicationEntity.class);
            entityManager.persist(adoptionApplication);
            adoptionApplicationList.add(adoptionApplication);

            AdoptionFollowUpEntity adoptionFollowUp = factory.manufacturePojo(AdoptionFollowUpEntity.class);
            entityManager.persist(adoptionFollowUp);
            adoptionFollowUpList.add(adoptionFollowUp);
            
            AdoptionEntity adoption = factory.manufacturePojo(AdoptionEntity.class);
            entityManager.persist(adoption);
            adoption.setPet(pet);
            adoptionList.add(adoption);
        }
        for (int i = 0; i < n; i++) {
            VeterinarianEntity veterinarian = factory.manufacturePojo(VeterinarianEntity.class);
            veterinarian.setMedicalEvents(medicalEventList);
            veterinarian.setDisponibilities(disponibilityList);
            veterinarian.setAdoptionApplications(adoptionApplicationList);
            veterinarian.setFollowUps(adoptionFollowUpList);
            veterinarian.setAdoption(adoptionList.get(i));
            entityManager.persist(veterinarian);
            veterinarianList.add(veterinarian);
        }
    }

    /**
     * Test for createVeterinarian method
     */
    @Test
    void testCreateVeterinarian() {
        VeterinarianEntity veterinarian = factory.manufacturePojo(VeterinarianEntity.class);
        veterinarian.setLicenseNumber("123456");
        veterinarian.setSpeciality(Speciality.CARDIOLOGY);
        VeterinarianEntity createdVeterinarian = veterinarianService.createVeterinarian(veterinarian);
        assertNotNull(createdVeterinarian);
        assertEquals(veterinarian.getLicenseNumber(), createdVeterinarian.getLicenseNumber());
        assertEquals(veterinarian.getSpeciality(), createdVeterinarian.getSpeciality());
    }
    /**
     * Test for getAllVeterinarians method
     */
    @Test
    void testGetAllVeterinarians() {
        List<VeterinarianEntity> veterinarians = veterinarianService.getAllVeterinarians();
        assertEquals(veterinarianList.size(), veterinarians.size());
    }
    /**
     * Test for getVeterinarianById method
     */
    @Test
    void testGetVeterinarianById() {
        VeterinarianEntity veterinarian = veterinarianList.get(0);
        VeterinarianEntity foundVeterinarian = veterinarianService.getVeterinarianById(veterinarian.getId());
        assertNotNull(foundVeterinarian);
        assertEquals(veterinarian.getId(), foundVeterinarian.getId());
    }
    /**
     * Test for updateVeterinarian method
     * @throws Exception
     */
    @Test
    void testUpdateVeterinarian() throws Exception {
        VeterinarianEntity veterinarian = veterinarianList.get(0);
        VeterinarianEntity updatedVeterinarian = factory.manufacturePojo(VeterinarianEntity.class);
        updatedVeterinarian.setId(veterinarian.getId());
        updatedVeterinarian.setLicenseNumber("654321");
        updatedVeterinarian.setSpeciality(Speciality.INFECTOLOGY);
        VeterinarianEntity result = veterinarianService.updateVeterinarian(veterinarian.getId(), updatedVeterinarian);
        assertNotNull(result);
        assertEquals(updatedVeterinarian.getLicenseNumber(), result.getLicenseNumber());
        assertEquals(updatedVeterinarian.getSpeciality(), result.getSpeciality());
    }
    /**
     * Test for deleteVeterinarian method
     */
    @Test
    void testDeleteVeterinarian() {
        VeterinarianEntity veterinarian = veterinarianList.get(0);
        veterinarianService.deleteVeterinarian(veterinarian.getId());
        VeterinarianEntity deletedVeterinarian = entityManager.find(VeterinarianEntity.class, veterinarian.getId());
        assertNull(deletedVeterinarian);
    }
    /**
     * Test for deleteVeterinarian method with non-existing id
     */
    @Test
    void testDeleteVeterinarianWithNonExistingId() {
        VeterinarianEntity veterinarian = factory.manufacturePojo(VeterinarianEntity.class);
        veterinarian.setId(999L);
        veterinarianService.deleteVeterinarian(veterinarian.getId());
        VeterinarianEntity deletedVeterinarian = entityManager.find(VeterinarianEntity.class, veterinarian.getId());
        assertNull(deletedVeterinarian);
    }
    /**
     * Test for updateVeterinarian method with non-existing id
     */
    @Test
    void testUpdateVeterinarianWithNonExistingId() {
        VeterinarianEntity veterinarian = factory.manufacturePojo(VeterinarianEntity.class);
        
        VeterinarianEntity updatedVeterinarian = factory.manufacturePojo(VeterinarianEntity.class);
        updatedVeterinarian.setLicenseNumber("654321");
        updatedVeterinarian.setSpeciality(Speciality.DERMATOLOGY);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            veterinarianService.updateVeterinarian(999L, updatedVeterinarian);
        });
        assertNotNull(exception);
    }
    /**
     * Test for createVeterinarian method with null license number
     */
    @Test
    void testCreateVeterinarianWithNullLicenseNumber() {
        VeterinarianEntity veterinarian = factory.manufacturePojo(VeterinarianEntity.class);
        veterinarian.setLicenseNumber(null);
        veterinarian.setSpeciality(Speciality.CARDIOLOGY);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            veterinarianService.createVeterinarian(veterinarian);
        });
        assertNotNull(exception);
    }
    /**
     * Test for createVeterinarian method with null speciality
     */
    @Test
    void testCreateVeterinarianWithNullSpeciality() {
        VeterinarianEntity veterinarian = factory.manufacturePojo(VeterinarianEntity.class);
        veterinarian.setLicenseNumber("123456");
        veterinarian.setSpeciality(null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            veterinarianService.createVeterinarian(veterinarian);
        });
        assertNotNull(exception);
    }

    /**
     * Test for getVeterinarianById method with non-existing id
     */
    @Test
    void testGetVeterinarianByIdWithNonExistingId() {
        VeterinarianEntity veterinarian = veterinarianList.get(0);
        VeterinarianEntity foundVeterinarian = veterinarianService.getVeterinarianById(999L);
        assertNull(foundVeterinarian);
    }

}