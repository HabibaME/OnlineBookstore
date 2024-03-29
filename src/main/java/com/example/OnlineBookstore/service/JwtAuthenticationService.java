package com.example.OnlineBookstore.service;
import com.example.OnlineBookstore.dto.login.LoginRequestDTO;
import com.example.OnlineBookstore.dto.login.LoginResponseDTO;
import com.example.OnlineBookstore.dto.register.RegisterRequestDTO;
import com.example.OnlineBookstore.dto.register.RegisterResponseDTO;
import com.example.OnlineBookstore.entity.User;
import com.example.OnlineBookstore.enums.Role;
import com.example.OnlineBookstore.error.RecordNotFoundException;
import com.example.OnlineBookstore.repository.UserRepository;
import com.example.OnlineBookstore.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationService {

    private final UserRepository repository;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponseDTO register(@Valid RegisterRequestDTO dto) throws RecordNotFoundException {
        var user = User
                .builder()
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .role(Role.ROLE_USER)
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();


        try {
            user = repository.save(user);
            return RegisterResponseDTO
                    .builder()
                    .email(user.getEmail())
                    .userName(user.getUsername())
                    .role(user.getRole())
                    .build();
        } catch (Exception e) {
            System.out.println("Caught Exception");
            throw new RecordNotFoundException("There is already user with that user name");
        }
    }

    public LoginResponseDTO login(@Valid LoginRequestDTO dto) throws RecordNotFoundException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getUserName(),
                            dto.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new RecordNotFoundException("There is no authenticated user with that credentials");
        }

        var user = repository.findUserByUserName(dto.getUserName())
                .orElseThrow(() -> new RecordNotFoundException("There is no user with that email."));
        Map<String, Object> claims = Map.of("userId", user.getId(), "email", user.getEmail());
        var token = jwtService.generateToken(claims, user);
        user.setToken(token);
        repository.save(user);
        return LoginResponseDTO.builder()
                .isAuthenticated(true)
                .userName(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .token(user.getToken())
                .build();
    }
}
