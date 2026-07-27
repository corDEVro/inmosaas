package com.inmosaas.controller;


import com.inmosaas.model.User;
import com.inmosaas.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    // Inyeccion de dependencias por constructor
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // GET http://localhost:8080/api/users (Obtener todos los usuarios)
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // POST http://localhost:8080/api/users (Crear nuevo usuario)
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        // Validamos si el email ya existe en la base de datos
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El email ya está registrado en el sistema");
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

}
