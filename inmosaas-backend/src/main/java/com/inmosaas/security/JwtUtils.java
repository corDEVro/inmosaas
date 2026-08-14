package com.inmosaas.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {

    // La clave secreta debe tener al menos 256 bits (32 caracteres) para el algoritmo HS256.
    // Se configura con la variable de entorno JWT_SECRET (o el valor por defecto de desarrollo).
    @Value("${JWT_SECRET:dev-inmosaas-secret-change-me-in-production-2026}")
    private String SECRET_KEY;
    private final long EXPIRATION_TIME = 86400000; // 24 horas en milisegundos

    private SecretKey getSingningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // 1. Generar token JWT
    public String generateToken(String email, UUID userId) {
        return Jwts.builder()
                .subject(email)
                .claim("userId", userId.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSingningKey())
                .compact();
    }

    // 2. Extraer el email del usuario desde el token
    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    // 3. Validar si el token es autentico y no ha expirado
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSingningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
