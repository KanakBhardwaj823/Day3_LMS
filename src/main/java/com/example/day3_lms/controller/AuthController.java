package com.example.day3_lms.controller;

import com.example.day3_lms.dto.LoginRequestDTO;
import com.example.day3_lms.dto.RegisterRequestDTO;
import com.example.day3_lms.dto.TokenResponseDTO;
import com.example.day3_lms.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    public final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public TokenResponseDTO login(
            @RequestBody LoginRequestDTO dto) {
        return service.login(dto);
    }

    @PostMapping("/register")
    public TokenResponseDTO register(
            @Valid @RequestBody RegisterRequestDTO dto) {
        return service.register(dto);
    }
}
