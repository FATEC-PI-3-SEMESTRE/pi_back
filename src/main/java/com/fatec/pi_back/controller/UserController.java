package com.fatec.pi_back.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fatec.pi_back.domain.User.User;
import com.fatec.pi_back.domain.User.UserDTO;
import com.fatec.pi_back.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> user = service.getUserById(id);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserDTO body) {
        Optional<User> updatedUser = service.updateUser(id, body);
        return updatedUser.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("password/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        String newPassword = (String) body.get("password");
        Long updatedBy = Long.valueOf(body.get("updated_by").toString());
        Optional<User> updatedUser = service.updatePassword(id, newPassword, updatedBy);
        return updatedUser.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("access/{id}")
    public ResponseEntity<Void> toggleAccess(@PathVariable Long id) {
        boolean deleted = service.toggleUserAccess(id);
        return deleted ? ResponseEntity.noContent().build()
                       : ResponseEntity.notFound().build();
    }

}