package com.inmosaas.controller;

import com.inmosaas.dto.request.PropertyCreateDTO;
import com.inmosaas.dto.response.PropertyResponseDTO;
import com.inmosaas.mapper.DTOMapper;
import com.inmosaas.model.Property;
import com.inmosaas.repository.PropertyRepository;
import com.inmosaas.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final DTOMapper dtoMapper;

    // Inyectamos el DTOMapper junto a los repositorios
    public PropertyController(PropertyRepository propertyRepository,
                              UserRepository userRepository,
                              DTOMapper dtoMapper) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
        this.dtoMapper = dtoMapper;
    }

    // GET /api/properties -> Devuelve lista de DTOs
    @GetMapping
    public ResponseEntity<List<PropertyResponseDTO>> getAllProperties() {
        List<PropertyResponseDTO> properties = propertyRepository.findAll()
                .stream()
                .map(dtoMapper::toPropertyResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(properties);
    }

    // GET /api/properties/user/{userId} -> Devuelve lista de DTOs del usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PropertyResponseDTO>> getPropertiesByUser(@PathVariable UUID userId) {
        List<PropertyResponseDTO> properties = propertyRepository.findByUserId(userId)
                .stream()
                .map(dtoMapper::toPropertyResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(properties);
    }

    // POST /api/properties/user/{userId} -> Guarda la entidad y devuelve el DTO filtrado
    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createProperty(@PathVariable UUID userId, @Valid @RequestBody PropertyCreateDTO propertyDTO) {
        return userRepository.findById(userId)
                .map(user -> {
                    // 1. Convertimos el DTO de entrada a entidad Property
                    Property propertyEntity = dtoMapper.toPropertyEntity(propertyDTO);

                    // 2. Vinculamos el usuario a la propiedad
                    propertyEntity.setUser(user);

                    // 3. Guardamos en la base de datos
                    Property savedProperty = propertyRepository.save(propertyEntity)
                            ;
                    // 4. Devoñlvemos la respuesta formateada como DTO de salida
                    PropertyResponseDTO responseDTO = dtoMapper.toPropertyResponseDTO(savedProperty);
                    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
                })
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
}