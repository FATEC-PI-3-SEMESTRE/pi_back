package com.fatec.pi_back.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fatec.pi_back.service.MedicationService;

import com.fatec.pi_back.domain.Medication.Medication;


@RestController
@RequestMapping("/medication")
public class MedicationController {

    @Autowired
    private MedicationService service;

    @GetMapping
    public ResponseEntity<List<Medication>> getAll() {
        return ResponseEntity.ok(service.getAllMedications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medication> getById(@PathVariable Long id) {
        Optional<Medication> medication = service.getMedicationById(id);
        return medication.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Medication> create(@RequestBody Medication medication) {
        Medication saved = service.createMedication(medication);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medication> update(@PathVariable Long id, @RequestBody Medication body) {
        Optional<Medication> updated = service.updateMedication(id, body);
        return updated.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        boolean deleted = service.deleteMedication(id);
        return deleted ? ResponseEntity.noContent().build()
                       : ResponseEntity.notFound().build();
    }
}
