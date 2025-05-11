package com.fatec.pi_back.domain.Patient;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fatec.pi_back.domain.User.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Patient {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer age;
    private String condition;
    private Boolean selfCare;

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
    
    public Patient(String name, Integer age, String condition, Boolean selfCare){
        this.name = name;
        this.age = age;
        this.condition = condition;
        this.selfCare = selfCare;
    }
}
