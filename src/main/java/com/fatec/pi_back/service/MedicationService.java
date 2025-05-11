package com.fatec.pi_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.pi_back.domain.Medication.Medication;
import com.fatec.pi_back.domain.Medication.MedicationDTO;
import com.fatec.pi_back.domain.User.User;
import com.fatec.pi_back.repository.MedicationRepository;
import com.fatec.pi_back.repository.UserRepository;

@Service
public class MedicationService {
    private final MedicationRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository, UserRepository userRepository) {
        this.repository = medicationRepository;
        this.userRepository = userRepository;
    }

    public List<Medication> getAllMedications() {
        return repository.findAll();
    }

    public Optional<Medication> getMedicationById(Long id) {
        return repository.findById(id);
    }

    public Medication createMedication(MedicationDTO dto) {
        Medication medication = new Medication();
        medication.setName(dto.name());
        medication.setMedication_function(dto.medication_function());
        medication.setDosageWeight(dto.dosageWeight());

        User createdBy = userRepository.findById(dto.UserId())
                                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        medication.setCreated_by(createdBy);
        medication.setUpdated_by(createdBy);
        return repository.save(medication);
    }

    public Optional<Medication> updateMedication(Long id, MedicationDTO updatedData) {
        return repository.findById(id).map(existing -> {
            existing.setName(updatedData.name());
            existing.setMedication_function(updatedData.medication_function());
            existing.setDosageWeight(updatedData.dosageWeight());
            User updatedBy = userRepository.findById(updatedData.UserId())
                                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            existing.setUpdated_by(updatedBy);
            return repository.save(existing);
        });
    }

    public boolean deleteMedication(Long id, Long UserID) {
        return repository.findById(id).map(medication -> {
            medication.setDeleted(!medication.isDeleted());
            User updatedBy = userRepository.findById(UserID)
                                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            medication.setUpdated_by(updatedBy);
            repository.save(medication);
            return true;
        }).orElse(false);
    }
}
