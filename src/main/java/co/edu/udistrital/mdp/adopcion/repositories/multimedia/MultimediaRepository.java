package co.edu.udistrital.mdp.adopcion.repositories.multimedia;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.udistrital.mdp.adopcion.entities.multimedia.MultimediaEntity;

public interface MultimediaRepository  extends JpaRepository<MultimediaEntity, Long> {
    
}