package com.fatec.pi_back.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.pi_back.domain.Login.LoginDTO;
import com.fatec.pi_back.domain.Login.LoginResponseDTO;
import com.fatec.pi_back.domain.User.User;
import com.fatec.pi_back.domain.User.UserDTO;
import com.fatec.pi_back.repository.UserRepository;
import com.fatec.pi_back.service.TokenService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

    @RestController
    @RequestMapping("auth")
    public class AuthenticatonController {
        @Autowired
        private UserRepository repository;

        @Autowired
        private TokenService tokenService;

        @Autowired
        private AuthenticationManager authenticationManager;

        @PostMapping("/login")
        public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO data) {
            var userPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());            
            var auth = this.authenticationManager.authenticate(userPassword);
            System.out.println(auth);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));

        }

        @PostMapping("/register")
        public ResponseEntity<String> register(@RequestBody @Valid UserDTO data) {
            if(this.repository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
            User newUser = new User(data.email(), encryptedPassword, data.access());

            this.repository.save(newUser);
            return ResponseEntity.ok().build();

        }
    }
