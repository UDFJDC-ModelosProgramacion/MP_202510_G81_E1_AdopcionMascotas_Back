package co.edu.udistrital.mdp.adopcion.controllers.adoption;

import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionTestEntity;
import co.edu.udistrital.mdp.adopcion.exceptions.EntityNotFoundException;
import co.edu.udistrital.mdp.adopcion.exceptions.IllegalOperationException;
import co.edu.udistrital.mdp.adopcion.services.adoption.AdoptionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adoption-tests")
public class AdoptionTestController {

    @Autowired
    private AdoptionTestService adoptionTestService;

    @PostMapping
    public ResponseEntity<AdoptionTestEntity> create(@RequestBody AdoptionTestEntity test) throws IllegalOperationException {
        return ResponseEntity.ok(adoptionTestService.createTest(test));
    }

    @GetMapping
    public ResponseEntity<List<AdoptionTestEntity>> getAll() {
        return ResponseEntity.ok(adoptionTestService.getAllTests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdoptionTestEntity> getById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(adoptionTestService.getTestById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptionTestEntity> update(@PathVariable Long id, @RequestBody AdoptionTestEntity test) throws EntityNotFoundException {
        return ResponseEntity.ok(adoptionTestService.updateTest(id, test));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws EntityNotFoundException {
        adoptionTestService.deleteTest(id);
        return ResponseEntity.noContent().build();
    }
}

