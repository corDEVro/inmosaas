package com.inmosaas.controller;

import com.inmosaas.dto.request.LoginRequestDTO;
import com.inmosaas.dto.response.AuthResponseDTO;
import com.inmosaas.model.User;
import com.inmosaas.repository.UserRepository;
import com.inmosaas.security.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        // 1. Buscar usuario por email
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        if (userOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        }

        User user = userOptional.get();

        // 2. Comprobar si la contraseña coincide con el hash encriptado
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        }

        // 3. Generar el Token JWT
        String token = jwtUtils.generateToken(user.getEmail(), user.getId());

        // 4. Devolver la respuesta con el token
        AuthResponseDTO response = new AuthResponseDTO(
                token,
                user.getId(),
                user.getEmail(),
                user.getName()
        );

        return ResponseEntity.ok(response);
    }
}