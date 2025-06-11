package co.edu.udistrital.mdp.adopcion.controllers.multimedia;

import co.edu.udistrital.mdp.adopcion.entities.multimedia.MultimediaEntity;
import co.edu.udistrital.mdp.adopcion.exceptions.EntityNotFoundException;
import co.edu.udistrital.mdp.adopcion.services.multimedia.MultimediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/multimedia")
@RequiredArgsConstructor
public class MultimediaController {

    private final MultimediaService multimediaService;

    @PostMapping
    public ResponseEntity<MultimediaEntity> createMultimedia(@RequestBody MultimediaEntity multimedia) {
        return ResponseEntity.ok(multimediaService.createMultimedia(multimedia));
    }

    @GetMapping
    public ResponseEntity<List<MultimediaEntity>> getAllMultimedia() {
        return ResponseEntity.ok(multimediaService.getAllMultimedia());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MultimediaEntity> getMultimediaById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(multimediaService.getMultimediaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MultimediaEntity> updateMultimedia(@PathVariable Long id, @RequestBody MultimediaEntity multimediaDetails) throws EntityNotFoundException {
        return ResponseEntity.ok(multimediaService.updateMultimedia(id, multimediaDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMultimedia(@PathVariable Long id) throws EntityNotFoundException {
        multimediaService.deleteMultimedia(id);
        return ResponseEntity.noContent().build();
    }
}

