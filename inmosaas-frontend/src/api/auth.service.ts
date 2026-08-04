import api from './axios';
import type {LoginRequest, RegisterRequest, AuthResponse} from '../types/auth.types';

export const authService = {
    // Petición para registrar un nuevo usuario
    register: async (data: RegisterRequest): Promise<AuthResponse> => {
        const response = await api.post('/auth/register', data);
        return response.data;
    },

    // Petición de inicio de sesion
    login: async (data: LoginRequest): Promise<AuthResponse> => {
        const response = await api.post('/auth/login', data);
        return response.data;
    },
};