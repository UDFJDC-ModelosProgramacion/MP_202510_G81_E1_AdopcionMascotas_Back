package co.edu.udistrital.mdp.adopcion.services.person;

package co.edu.udistrital.mdp.adopcion.services.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.udistrital.mdp.adopcion.entities.person.OwnerEntity;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionAplicationRepository;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionFollowUpRepository;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionRepository;
import co.edu.udistrital.mdp.adopcion.repositories.adoption.AdoptionTestRepository;
import co.edu.udistrital.mdp.adopcion.repositories.event.MedicalEventRepository;
import co.edu.udistrital.mdp.adopcion.repositories.person.DisponibilityRepository;
import co.edu.udistrital.mdp.adopcion.repositories.person.OwnerRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private MedicalEventRepository medicalEventRepository;
    @Autowired
    private DisponibilityRepository disponibilityRepository;
    @Autowired
    private AdoptionAplicationRepository adoptionApplicationRepository;
    @Autowired
    private AdoptionFollowUpRepository adoptionFollowUpRepository;
    @Autowired
    private AdoptionRepository adoptionRepository;
    @Autowired
    private AdoptionTestRepository adoptionTestRepository;

    @Transactional
    public OwnerEntity createOwner(OwnerEntity owner) {
        // Validar campos de PersonEntity
        if (owner.getFirstName() == null || owner.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("The name must not be empty");
        }
        
        if (owner.getLastName() == null || owner.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("The last name must not be empty");
        }
        
        // Aquí falta el campo de identificación que mencionaste como requerido
        // Asumo que debe estar en otra parte del código o se debe agregar a la entidad
        if (owner.getIdentificationNumber() == null || owner.getIdentificationNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("The identification number must not be empty");
        }
        
        if (owner.getPhoneNumber() == null || owner.getPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("The phone number must not be empty");
        }
        
        if (owner.getEmail() == null || owner.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("The email must not be empty");
        }
        
        // Campos específicos de OwnerEntity
        if (owner.getAddress() == null || owner.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("The address must not be empty");
        }
        
        if (owner.getHouseType() == null) {
            throw new IllegalArgumentException("The house type must not be null");
        }
        
        // Verificar que el número de identificación sea único
        if (ownerRepository.existsByIdentificationNumber(owner.getIdentificationNumber())) {
            throw new IllegalArgumentException("The identification number already exists");
        }
        
        // Guardar el propietario en la base de datos
        return ownerRepository.save(owner);
    }
    @Transactional
    public OwnerEntity updateOwner(Long id, OwnerEntity owner, boolean isAdmin) {
        if (!ownerRepository.existsById(id)) {
            return null; // No existe el propietario con ese ID
        }
        
        // Obtener el propietario existente en la base de datos
        OwnerEntity existingOwner = ownerRepository.findById(id).orElse(null);
        if (existingOwner == null) {
            return null;
        }
        
        // Verificar si el número de identificación ha cambiado
        if (owner.getIdentificationNumber() != null && 
            !owner.getIdentificationNumber().equals(existingOwner.getIdentificationNumber())) {
            
            // Solo permitir actualizar la identificación si es administrador
            if (!isAdmin) {
                throw new SecurityException("Only an admin can change the identification number");
            }
            
            // Verificar que el nuevo número de identificación sea único
            if (ownerRepository.existsByIdentificationNumber(owner.getIdentificationNumber())) {
                throw new IllegalArgumentException("Your identification number already exists");
            }
        }
        
        // Establecer el ID y guardar los cambios
        owner.setId(id);
        
        // Copiar las asociaciones existentes para evitar perderlas
        owner.setAdoptions(existingOwner.getAdoptions());
        owner.setAdoptionTests(existingOwner.getAdoptionTests());
        owner.setAdoptionApplications(existingOwner.getAdoptionApplications());
        owner.setPets(existingOwner.getPets());
        
        return ownerRepository.save(owner);
    }
    @Transactional
    public void deleteOwner(Long id, boolean isAdmin) {
        // Verificar si el usuario tiene rol de administrador
        if (!isAdmin) {
            throw new SecurityException("Only an admin can delete an owner");
        }
        
        // Verificar que el propietario exista
        if (!ownerRepository.existsById(id)) {
            throw new IllegalArgumentException("The owner with the given ID does not exist");
        }
        
        // Eliminar las asociaciones relacionadas antes de eliminar el propietario
        medicalEventRepository.deleteByOwnerId(id);
        disponibilityRepository.deleteByOwnerId(id);
        adoptionApplicationRepository.deleteByOwnerId(id);
        adoptionFollowUpRepository.deleteByOwnerId(id);
        adoptionRepository.deleteByOwnerId(id);
        adoptionTestRepository.deleteByOwnerId(id);
        
        // Eliminar el propietario
        ownerRepository.deleteById(id);
    }
}