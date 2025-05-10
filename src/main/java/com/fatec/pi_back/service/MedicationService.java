package com.fatec.pi_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.pi_back.domain.Medication.Medication;
import com.fatec.pi_back.repository.MedicationRepository;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository repository;

    public List<Medication> getAllMedications() {
        return repository.findAll();
    }

    public Optional<Medication> getMedicationById(Long id) {
        return repository.findById(id);
    }

    public Medication createMedication(Medication medication) {
        return repository.save(medication);
    }

    public Optional<Medication> updateMedication(Long id, Medication updatedData) {
        return repository.findById(id).map(existing -> {
            existing.setName(updatedData.getName());
            existing.setMedication_function(updatedData.getMedication_function());
            existing.setDosageWeight(updatedData.getDosageWeight());
            existing.setUpdatedBy(updatedData.getUpdatedBy());
            return repository.save(existing);
        });
    }

    public boolean deleteMedication(Long id) {
        return repository.findById(id).map(medication -> {
            medication.setDeleted(true);
            repository.save(medication);
            return true;
        }).orElse(false);
    }
}
