package co.edu.udistrital.mdp.adopcion.entities.adoption;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.entities.person.VeterinarianEntity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@MappedSuperclass
public class AdoptionTestEntity extends AdoptionEntity {
    @PodamExclude
    @OneToMany(mappedBy="adoptionTest")
    private List<OwnerEntity> owners = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy="adoptionTest")
    private List<VeterinarianEntity> veterinarians = new ArrayList<>();

    
    String description;
    Date testEnd;
    String testObservations;
    TestResultEnum  testResult;
    

}
