package com.inmosaas.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyCreateDTO {

    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    private String description;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String address;

    @NotBlank(message = "La ciudad no puede estar vacía")
    private String city;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private BigDecimal price;

    @Min(value = 0, message = "El número de hab. no puede ser negativo")
    private Integer bedrooms;

    @Min(value = 0, message = "El número de baños no puede ser negativo")
    private Integer bathrooms;

    @Min(value = 1, message = "Los m² deben de ser al menos 1")
    private Integer squareMeters;
}