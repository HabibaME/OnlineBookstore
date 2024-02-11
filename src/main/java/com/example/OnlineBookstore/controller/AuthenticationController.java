package com.example.OnlineBookstore.controller;


import com.example.OnlineBookstore.dto.login.LoginRequestDTO;
import com.example.OnlineBookstore.dto.login.LoginResponseDTO;
import com.example.OnlineBookstore.dto.register.RegisterRequestDTO;
import com.example.OnlineBookstore.error.RecordNotFoundException;
import com.example.OnlineBookstore.service.JwtAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtAuthenticationService authService;

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) throws RecordNotFoundException {
        return ResponseEntity.ok(authService.login(dto));
    }
}
