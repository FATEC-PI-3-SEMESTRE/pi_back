package com.fatec.pi_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.pi_back.domain.Patient.Patient;

public interface PatientRepository extends JpaRepository <Patient,Long>{
    
}
   

