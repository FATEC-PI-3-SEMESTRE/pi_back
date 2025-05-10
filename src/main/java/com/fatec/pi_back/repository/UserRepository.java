package com.fatec.pi_back.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.fatec.pi_back.domain.User.User;


public interface UserRepository extends JpaRepository <User, Integer>{
    UserDetails findByEmail(String email);
    
}
