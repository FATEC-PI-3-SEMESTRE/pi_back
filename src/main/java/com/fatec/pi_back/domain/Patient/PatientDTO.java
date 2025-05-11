package com.fatec.pi_back.domain.Patient;

public record PatientDTO (String name, Integer age, String condition, Boolean selfCare, Long UserID) {
    
}