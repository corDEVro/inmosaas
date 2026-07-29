package com.inmosaas.controller;

import com.inmosaas.dto.response.UserResponseDTO;
import com.inmosaas.mapper.DTOMapper;
import com.inmosaas.model.User;
import com.inmosaas.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final DTOMapper dtoMapper;

    // Inyectamos UserRepository y DTOMapper
    public UserController(UserRepository userRepository, DTOMapper dtoMapper) {
        this.userRepository = userRepository;
        this.dtoMapper = dtoMapper;
    }

    // GET /api/users -> Lista de usuarios mapeada a DTO
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userRepository.findAll()
                .stream()
                .map(dtoMapper::toUserResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    // POST /api/users -> Crea usuario y devuelve UserResponseDTO sin password
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("El email ya está registrado");
        }
        User savedUser = userRepository.save(user);
        UserResponseDTO responseDTO = dtoMapper.toUserResponseDTO(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}