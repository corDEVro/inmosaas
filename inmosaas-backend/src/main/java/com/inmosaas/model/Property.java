package com.inmosaas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "El título del inmueble es obligatorio")
    @Column(nullable = false)
    private String title;

    private String description;

    @NotBlank(message = "La dirección es obligatoria")
    @Column(nullable = false)
    private String address;

    @NotBlank(message = "La ciudad es obligatoria")
    @Column(nullable = false)
    private String city;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser un valor positivo")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message = "El número de habitaciones es obligatorio")
    @Positive(message = "Las habitaciones deben ser al menos 1")
    private Integer bedrooms;

    @NotNull(message = "El número de baños es obligatorio")
    @Positive(message = "Los baños deben ser al menos 1")
    private Integer bathrooms;

    private Integer squareMeters;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
