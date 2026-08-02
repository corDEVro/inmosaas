package com.inmosaas.controller;

import com.inmosaas.dto.request.UserCreateDTO;
import com.inmosaas.dto.response.UserResponseDTO;
import com.inmosaas.mapper.DTOMapper;
import com.inmosaas.model.User;
import com.inmosaas.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final DTOMapper dtoMapper;
    private final PasswordEncoder passwordEncoder; // Inyectamos el encriptador

    // Inyectamos UserRepository y DTOMapper
    public UserController(UserRepository userRepository, DTOMapper dtoMapper,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.dtoMapper = dtoMapper;
        this.passwordEncoder = passwordEncoder;
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
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("El email ya está registrado");
        }

        // 1. Convertimos el DTO a Entidad
        User userEntity = dtoMapper.toUserEntity(userDTO);

        // 1B. ENCRIPTACION BCRYPT: Ciframos la contraseña antes de guardar en PostgreSQL
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userEntity.setPassword(encodedPassword);

        // 2. Guardamos la entidad en la base de datos
        User savedUser = userRepository.save(userEntity);

        // 3. Convertimos la entidad guardada al DTO de respuesta seguro
        UserResponseDTO responseDTO = dtoMapper.toUserResponseDTO(savedUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}