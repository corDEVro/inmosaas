package com.inmosaas.controller;

import com.inmosaas.model.Property;
import com.inmosaas.model.User;
import com.inmosaas.repository.PropertyRepository;
import com.inmosaas.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    // Inyección de dependencias por constructor
    public PropertyController(PropertyRepository propertyRepository, UserRepository userRepository) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    // GET http://localhost:8080/api/properties (Listar todos los inmuebles)
    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        return ResponseEntity.ok(properties);
    }

    // GET http://localhost:8080/api/properties/user/{userId} (Listar los inmuebles de un asesor)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Property>> getPropertiesByUser(@PathVariable UUID userId) {
        List<Property> properties = propertyRepository.findByUserId(userId);
        return ResponseEntity.ok(properties);
    }

    // POST http://localhost:8080/api/properties/user/{userId} (Crear un inmueble asignado a un usuario)
    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createProperty(@PathVariable UUID userId, @Valid @RequestBody Property property) {
        // 1. Buscamos si el usuario existe en la base de datos
        return userRepository.findById(userId)
                .map(user -> {
                    // 2. Si existe, vinculamos la propiedad al usuario
                    property.setUser(user);
                    // 3. Guardamos la propiedad en la base de datos
                    Property savedProperty = propertyRepository.save(property);
                    return ResponseEntity.status(HttpStatus.CREATED).body(savedProperty);
                })
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null));
    }
}