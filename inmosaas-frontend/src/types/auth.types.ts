// Datos requeridos para el registro
export interface RegisterRequest {
    nombre: string;
    emaiil: string;
    password: string;
}

// Datos requeridos para el Login
export interface LoginRequest {
    email: string;
    password: string;
}

// Respuesta que nos devuelve el backend al hacer Login/Registro
export interface AuthResponse {
    token: string;
    email: string;
    nombre?: string;
}

// Estructura del usuario autenticando en la app
export interface User {
    email: string;
    nombre?: string;
}