package com.fatec.pi_back.domain.User;

public record UserDTO (String email, String password, Boolean access, Long UserID) {
    
}
