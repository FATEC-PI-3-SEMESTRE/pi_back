package com.fatec.pi_back.domain.Medication;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fatec.pi_back.domain.User.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medication")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 255)
    private String medication_function;

    @Column(name = "dosage_weight")
    private Float dosageWeight;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name = "created_by", updatable = false)
    private User created_by;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updated_by;

    @Column(nullable = false)
    private boolean deleted = false;

    public Medication(String name, String medication_function, Float dosageWeight) {
        this.name = name;
        this.medication_function = medication_function;
        this.dosageWeight = dosageWeight;
    }
}
