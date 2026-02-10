package com.example.day3_lms.service;

import com.example.day3_lms.dto.*;
import com.example.day3_lms.model.UserModel;
import com.example.day3_lms.repository.UserRepository;
import com.example.day3_lms.Util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository repository, JwtUtil jwtUtil) {
        this.repository = repository;
        this.jwtUtil = jwtUtil;
    }

    public TokenResponseDTO login(LoginRequestDTO dto) {

        UserModel user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new TokenResponseDTO(token);
    }

    public TokenResponseDTO register(RegisterRequestDTO dto) {

        // Check if user already exists
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // Create new user
        UserModel newUser = new UserModel();
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(dto.getPassword()); // Note: In production, hash the password!

        repository.save(newUser);

        // Generate token for the new user
        String token = jwtUtil.generateToken(newUser.getEmail());

        return new TokenResponseDTO(token);
    }
}