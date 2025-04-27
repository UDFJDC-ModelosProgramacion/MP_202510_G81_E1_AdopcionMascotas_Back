package co.edu.udistrital.mdp.adopcion.services.events;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.udistrital.mdp.adopcion.entities.events.MedicalEventEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import co.edu.udistrital.mdp.adopcion.entities.pet.PetEntity;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(MedicalEventService.class)
public class MedicalEventServiceTest {
    @Autowired
    private MedicalEventService medicalEventService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();
    private List<MedicalEventEntity> medicalEventList;
    private List<VeterinarianEntity> veterinarianList;
    private List<PetEntity> petList;
    private List<Date> dateList;
    private List<String> descriptionList;

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from MedicalEventEntity");
        entityManager.getEntityManager().createQuery("delete from PetEntity");
        entityManager.getEntityManager().createQuery("delete from VeterinarianEntity");
    }

    private void insertData() {
        int n = 5;
        for (int i = 0; i < n; i++) {
            VeterinarianEntity veterinarian = factory.manufacturePojo(VeterinarianEntity.class);
            entityManager.persist(veterinarian);
            veterinarianList.add(veterinarian);

            PetEntity pet = factory.manufacturePojo(PetEntity.class);
            entityManager.persist(pet);
            petList.add(pet);

            Date date = factory.manufacturePojo(Date.class);
            entityManager.persist(date);
            dateList.add(date);

            String description = factory.manufacturePojo(String.class);
            entityManager.persist(description);
            descriptionList.add(description);
        }

        for (int i = 0; i < n; i++) {
            MedicalEventEntity medicalEvent = factory.manufacturePojo(MedicalEventEntity.class);
            medicalEvent.setVeterinarian(veterinarianList.get(i));
            medicalEvent.setPet(petList.get(i));
            medicalEvent.setDate(dateList.get(i));
            medicalEvent.setDescription(descriptionList.get(i));
            entityManager.persist(medicalEvent);
            medicalEventList.add(medicalEvent);
        }
    }

    /**
     * Test for createMedicalEvent method
     */
    @Test
    void testCreateMedicalEvent() {
        MedicalEventEntity medicalEvent = factory.manufacturePojo(MedicalEventEntity.class);
        medicalEvent.setVeterinarian(veterinarianList.get(0));
        medicalEvent.setPet(petList.get(0));
        medicalEvent.setDate(dateList.get(0));
        medicalEvent.setDescription(descriptionList.get(0));
        
        MedicalEventEntity createdMedicalEvent = medicalEventService.createMedicalEvent(medicalEvent);
        assertNotNull(createdMedicalEvent);
        MedicalEventEntity foundMedicalEvent = entityManager.find(MedicalEventEntity.class, createdMedicalEvent.getId());
        assertEquals(medicalEvent.getVeterinarian().getId(), foundMedicalEvent.getVeterinarian().getId());
        assertEquals(medicalEvent.getPet().getId(), foundMedicalEvent.getPet().getId());
        assertEquals(medicalEvent.getDate(), foundMedicalEvent.getDate());
        assertEquals(medicalEvent.getDescription(), foundMedicalEvent.getDescription());
    }

    @Test
    void testCreateMedicalEventWithNullVeterinarian() {
        MedicalEventEntity medicalEvent = factory.manufacturePojo(MedicalEventEntity.class);
        medicalEvent.setVeterinarian(null);
        medicalEvent.setPet(petList.get(0));
        medicalEvent.setDate(dateList.get(0));
        medicalEvent.setDescription(descriptionList.get(0));

        assertThrows(IllegalArgumentException.class, () -> {
            medicalEventService.createMedicalEvent(medicalEvent);
        });
    }

    @Test
    void testCreateMedicalEventWithNullPet() {
        MedicalEventEntity medicalEvent = factory.manufacturePojo(MedicalEventEntity.class);
        medicalEvent.setVeterinarian(veterinarianList.get(0));
        medicalEvent.setPet(null);
        medicalEvent.setDate(dateList.get(0));
        medicalEvent.setDescription(descriptionList.get(0));

        assertThrows(IllegalArgumentException.class, () -> {
            medicalEventService.createMedicalEvent(medicalEvent);
        });
    }

    @Test
    void testCreateMedicalEventWithValidParams(){
        MedicalEventEntity medicalEvent = factory.manufacturePojo(MedicalEventEntity.class);
        medicalEvent.setVeterinarian(veterinarianList.get(0));
        medicalEvent.setPet(petList.get(0));
        medicalEvent.setDate(dateList.get(0));
        medicalEvent.setDescription(descriptionList.get(0));

        MedicalEventEntity createdMedicalEvent = medicalEventService.createMedicalEvent(medicalEvent);
        assertNotNull(createdMedicalEvent);
        assertEquals(medicalEvent.getVeterinarian().getId(), createdMedicalEvent.getVeterinarian().getId());
        assertEquals(medicalEvent.getPet().getId(), createdMedicalEvent.getPet().getId());
        assertEquals(medicalEvent.getDate(), createdMedicalEvent.getDate());
        assertEquals(medicalEvent.getDescription(), createdMedicalEvent.getDescription());
    }
}
