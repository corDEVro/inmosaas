# InmoSaaS

SaaS para la gestión de cartera de inmuebles, visitas y contratos para asesores inmobiliarios.

## 🛠️ Tecnologías

- **Backend:** Java 21 + Spring Boot 4.1 (Spring Security, JWT, Spring Data JPA)
- **Frontend:** React 19 + TypeScript + TailwindCSS + Vite
- **Base de datos:** PostgreSQL
- **Infraestructura:** Docker / Docker Compose

## 📁 Estructura

```text
inmosaas/
├── inmosaas-backend/        # API REST Spring Boot
│   └── src/main/java/com/inmosaas/
│       ├── controller/      # Auth (/api/auth), Users (/api/users), Properties (/api/properties)
│       ├── config/          # Configuración (CORS, seguridad)
│       ├── security/        # JWT (filtro + utilidades)
│       ├── repository/
│       ├── model/
│       ├── dto/
│       ├── mapper/
│       └── exception/       # Manejo global de errores
└── inmosaas-frontend/       # SPA React + TypeScript
    └── src/
        ├── api/             # Cliente axios con interceptor JWT
        ├── context/         # AuthContext (sesión del usuario)
        └── types/           # Tipos compartidos
```

## 🚀 Puesta en marcha

1. **Levanta la base de datos** (PostgreSQL en Docker):

   ```bash
   docker compose up -d
   ```

2. **Configura las variables de entorno:** copia `.env.example` a `.env` y ajusta los valores (credenciales de la BD y `JWT_SECRET`).

3. **Arranca el backend:**

   ```bash
   cd inmosaas-backend
   ./mvnw spring-boot:run
   ```

4. **Arranca el frontend:**

   ```bash
   cd inmosaas-frontend
   npm install
   npm run dev
   ```

## 🔐 Variables de entorno

| Variable | Descripción | Default |
| --- | --- | --- |
| `POSTGRES_DB` | Nombre de la base de datos | `inmosaas_db` |
| `POSTGRES_USER` | Usuario de PostgreSQL | `inmosaas_user` |
| `POSTGRES_PASSWORD` | Contraseña de PostgreSQL | `inmosaas_password` |
| `DB_URL` | JDBC URL del backend | `jdbc:postgresql://localhost:5432/inmosaas_db` |
| `DB_USERNAME` | Usuario JDBC del backend | `inmosaas_user` |
| `DB_PASSWORD` | Contraseña JDBC del backend | `inmosaas_password` |
| `JWT_SECRET` | Clave de firma de tokens JWT (mín. 32 chars) | `dev-inmosaas-secret-change-me-in-production-2026` |
