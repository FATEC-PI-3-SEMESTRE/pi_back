package com.fatec.pi_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.pi_back.domain.Patient.Patient;
import com.fatec.pi_back.domain.Patient.PatientDTO;
import com.fatec.pi_back.domain.User.User;
import com.fatec.pi_back.repository.PatientRepository;
import com.fatec.pi_back.repository.UserRepository;

@Service
public class PatientService {
    private final PatientRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, UserRepository userRepository) {
        this.repository = patientRepository;
        this.userRepository = userRepository;
    }

    public List<Patient> getAllPatients() {
        return repository.findAll();
    }

    public Optional<Patient> getPatientByID(Long id) {
        return repository.findById(id);
    }

    public Patient createPatient(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setName(dto.name());
        patient.setAge(dto.age());
        patient.setPatient_condition(dto.condition());
        patient.setSelfCare(dto.selfCare());

        User createdBy = userRepository.findById(dto.UserID())
                                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        patient.setCreated_by(createdBy);
        patient.setUpdated_by(createdBy);
        return repository.save(patient);
    }

    public Optional<Patient> updatePatient(Long id, PatientDTO updatedData) {
        return repository.findById(id).map(existing -> {
            existing.setName(updatedData.name());
            existing.setAge(updatedData.age());
            existing.setPatient_condition(updatedData.condition());
            existing.setSelfCare(updatedData.selfCare());
            User updatedBy = userRepository.findById(updatedData.UserID())
                                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            existing.setUpdated_by(updatedBy);
            return repository.save(existing);
        });
    }

    public boolean deletePatient(Long id, Long UserID) {
        return repository.findById(id).map(patient -> {
            patient.setDeleted(!patient.isDeleted());
            User updatedBy = userRepository.findById(UserID)
                                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            patient.setUpdated_by(updatedBy);
            repository.save(patient);
            return true;
        }).orElse(false);
    }
}
