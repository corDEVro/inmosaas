package com.inmosaas.mapper;

import com.inmosaas.dto.request.PropertyCreateDTO;
import com.inmosaas.dto.request.UserCreateDTO;
import com.inmosaas.dto.response.PropertyResponseDTO;
import com.inmosaas.dto.response.UserResponseDTO;
import com.inmosaas.model.Property;
import com.inmosaas.model.User;
import org.springframework.stereotype.Component;

@Component
public class DTOMapper {

    // --- CONVERSIONES DE SALIDA (Entity -> ResponseDTO) ---
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

        // Mapeamos el usuario anidado de forma segura usando el metodo anterior
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

    // --- CONVERSIONES DE ENTRADA (RequestDTO -> Entity) ---
    // Convierte el DTO de creacion de usuario a la entidad User para la base de datos
    public User toUserEntity(UserCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setAgencyName(dto.getAgencyName());
        return user;
    }

    // Convierte el DTO de creacion de propiedad a la entidad property para la base de datos
    public Property toPropertyEntity(PropertyCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        Property property = new Property();
        property.setTitle(dto.getTitle());
        property.setDescription(dto.getDescription());
        property.setAddress(dto.getAddress());
        property.setCity(dto.getCity());
        property.setPrice(dto.getPrice());
        property.setBedrooms(dto.getBedrooms());
        property.setBathrooms(dto.getBathrooms());
        property.setSquareMeters(dto.getSquareMeters());
        return property;
    }
}