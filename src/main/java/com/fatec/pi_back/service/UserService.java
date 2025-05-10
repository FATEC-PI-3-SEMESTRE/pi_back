package com.fatec.pi_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.pi_back.domain.User.*;
import com.fatec.pi_back.repository.UserRepository;

@Service    
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return repository.findById(id);
    }

    public Optional<User> updateUser(Integer id, UserDTO data) {
        return repository.findById(id).map(existingUser -> {
            existingUser.setEmail(data.email());
            existingUser.setPassword(data.password());
            existingUser.setAccess(data.access());
            existingUser.setUpdatedBy(data.User());
            return repository.save(existingUser);
        });
    }

    public boolean toggleUserAccess(Integer id) {
        return repository.findById(id).map(user -> {
            user.setDeleted(!user.isDeleted()); // inverte o valor atual
            repository.save(user);
            return true;
        }).orElse(false);
    }

}