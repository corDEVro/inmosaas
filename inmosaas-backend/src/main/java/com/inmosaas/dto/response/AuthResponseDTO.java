package com.inmosaas.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;
    private String tokenType;
    private UUID userId;
    private String email;
    private String name;

    // Constructor personalizado para inicializar con tokenType = "Bearer" por defecto
    public AuthResponseDTO(String token, UUID userId, String email, String name) {
        this.token = token;
        this.tokenType = "Bearer";
        this.userId = userId;
        this.email = email;
        this.name = name;
    }
}
