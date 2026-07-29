package com.inmosaas.repository;

import com.inmosaas.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRepository extends JpaRepository<Property, UUID> {

    // Método para buscar todas las propiedades que pertenecen a un usuario específico
    List<Property> findByUserId(UUID userId);

    // Método para buscar propiedades por ciudad (ignorando mayúsculas y minúsculas)
    List<Property> findByCityIgnoreCase(String city);
}