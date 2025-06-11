package co.edu.udistrital.mdp.adopcion.repositories.adoption;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.udistrital.mdp.adopcion.entities.adoption.AdoptionAplicationEntity;


public interface AdoptionApplicationRepository extends JpaRepository<AdoptionAplicationEntity, Long> {

}
