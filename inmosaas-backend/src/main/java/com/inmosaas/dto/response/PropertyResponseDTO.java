package com.inmosaas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private String address;
    private String city;
    private BigDecimal price;
    private Integer bedrooms;
    private Integer bathrooms;
    private Integer squareMeters;
    private UserResponseDTO user; // 👈 En lugar del modelo 'User' entero, usamos el DTO filtrado
    private LocalDateTime createdAt;
}