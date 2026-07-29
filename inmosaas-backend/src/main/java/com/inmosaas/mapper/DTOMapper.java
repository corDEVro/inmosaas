package com.inmosaas.mapper;

import com.inmosaas.dto.response.PropertyResponseDTO;
import com.inmosaas.dto.response.UserResponseDTO;
import com.inmosaas.model.Property;
import com.inmosaas.model.User;
import org.springframework.stereotype.Component;

@Component
public class DTOMapper {

    // Convierte una entidad User en un UserResponseDTO (sin password)
    public UserResponseDTO toUserResponseDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAgencyName(),
                user.getCreatedAt()
        );
    }

    // Convierte una entidad Property en un PropertyResponseDTO
    public PropertyResponseDTO toPropertyResponseDTO(Property property) {
        if (property == null) {
            return null;
        }

        // Mapeamos el usuario anidado de forma segura usando el método anterior
        UserResponseDTO userDTO = toUserResponseDTO(property.getUser());

        return new PropertyResponseDTO(
                property.getId(),
                property.getTitle(),
                property.getDescription(),
                property.getAddress(),
                property.getCity(),
                property.getPrice(),
                property.getBedrooms(),
                property.getBathrooms(),
                property.getSquareMeters(),
                userDTO, // 👈 Se asigna el usuario sin la contraseña
                property.getCreatedAt()
        );
    }
}