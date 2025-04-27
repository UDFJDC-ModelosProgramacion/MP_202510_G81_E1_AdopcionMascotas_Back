package co.edu.udistrital.mdp.adopcion.services.events.medical;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.udistrital.mdp.adopcion.entities.events.medical.DewormingEntity;
import co.edu.udistrital.mdp.adopcion.entities.events.medical.VaccineCardEntity;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@DataJpaTest
@Transactional
@Import(DewormingService.class)
public class DewormingServiceTest {
    @Autowired
    private DewormingService dewormingService;
    @Autowired
    private TestEntityManager entityManager;
    private PodamFactory factory = new PodamFactoryImpl();
    private List<DewormingEntity> dewormingList;
    private List<VaccineCardEntity> vaccineCardList;

    @BeforeEach
    void setUp() {
        clearData();
        insertData();
    }
    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from DewormingEntity");
        entityManager.getEntityManager().createQuery("delete from VaccineCardEntity");
    }
    private void insertData() {
        int n = 5;
        for (int i = 0; i < n; i++) {
            DewormingEntity deworming = factory.manufacturePojo(DewormingEntity.class);
            entityManager.persist(deworming);
            dewormingList.add(deworming);

            VaccineCardEntity vaccineCard = factory.manufacturePojo(VaccineCardEntity.class);
            entityManager.persist(vaccineCard);
            vaccineCardList.add(vaccineCard);
        }
    }
    /**
     * Test for createDeworming method
     */
    @Test
    void testCreateDeworming() {
        DewormingEntity deworming = factory.manufacturePojo(DewormingEntity.class);
        deworming.setVaccineCard(vaccineCardList.get(0));
        DewormingEntity createdDeworming = dewormingService.createDeworming(deworming);
        assertNotNull(createdDeworming);
        assertEquals(deworming.getBrandName(), createdDeworming.getBrandName());
        assertEquals(deworming.getBrandName(), createdDeworming.getBrandName());
        assertEquals(deworming.getNextDate(), createdDeworming.getNextDate());
        assertEquals(deworming.getDosis(), createdDeworming.getDosis());
    }
    /**
     * Test for getAllDewormings method
     */
    @Test
    void testGetAllDewormings() {
        List<DewormingEntity> dewormings = dewormingService.getAllDewormings();
        assertNotNull(dewormings);
        assertEquals(dewormingList.size(), dewormings.size());
        for (int i = 0; i < dewormingList.size(); i++) {
            assertEquals(dewormingList.get(i).getId(), dewormings.get(i).getId());
            assertEquals(dewormingList.get(i).getBrandName(), dewormings.get(i).getBrandName());
            assertEquals(dewormingList.get(i).getNextDate(), dewormings.get(i).getNextDate());
            assertEquals(dewormingList.get(i).getDosis(), dewormings.get(i).getDosis());
        }
    }
    /**
     * Test for getDewormingById method
     */
    @Test
    void testGetDewormingById() {
        DewormingEntity deworming = dewormingList.get(0);
        DewormingEntity foundDeworming = dewormingService.getDewormingById(deworming.getId());
        assertNotNull(foundDeworming);
        assertEquals(deworming.getBrandName(), foundDeworming.getBrandName());
        assertEquals(deworming.getBrandName(), foundDeworming.getBrandName());
        assertEquals(deworming.getNextDate(), foundDeworming.getNextDate());
        assertEquals(deworming.getDosis(), foundDeworming.getDosis());
    }
    /**
     * Test for updateDeworming method
     */
    @Test
    void testUpdateDeworming() {
        DewormingEntity deworming = dewormingList.get(0);
        DewormingEntity updatedDeworming = factory.manufacturePojo(DewormingEntity.class);
        updatedDeworming.setId(deworming.getId());
        updatedDeworming.setVaccineCard(vaccineCardList.get(0));
        DewormingEntity result = dewormingService.updateDeworming(deworming.getId(), updatedDeworming);
        assertNotNull(result);
        assertEquals(updatedDeworming.getBrandName(), result.getBrandName());
        assertEquals(updatedDeworming.getBrandName(), result.getBrandName());
        assertEquals(updatedDeworming.getNextDate(), result.getNextDate());
        assertEquals(updatedDeworming.getDosis(), result.getDosis());
    }
    /**
     * Test for deleteDeworming method
     */
    @Test
    void testDeleteDeworming() {
        DewormingEntity deworming = dewormingList.get(0);
        dewormingService.deleteDeworming(deworming.getId());
        DewormingEntity deletedDeworming = entityManager.find(DewormingEntity.class, deworming.getId());
        assertNull(deletedDeworming);
    }
}
