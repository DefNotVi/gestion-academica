# Gestion Académica - Arquitectura Refactorizada

## 🏗️ Estructura Arquitectónica

### Patrón: **Layered Architecture (Arquitectura en Capas)**

```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│   (Controllers + Exception Handlers)    │
└────────────────┬────────────────────────┘
                 │ DTOs
┌────────────────▼────────────────────────┐
│         Business Logic Layer            │
│         (Service Classes)               │
└────────────────┬────────────────────────┘
                 │ Entities
┌────────────────▼────────────────────────┐
│    Data Persistence Layer               │
│  (Repository Interfaces + JPA)          │
└────────────────┬────────────────────────┘
                 │ SQL
┌────────────────▼────────────────────────┐
│      Database Layer (PostgreSQL)        │
└─────────────────────────────────────────┘
```

---

## 📦 Componentes Principales

### 1. **Controllers** (`controller/`)
- Punto de entrada para las peticiones HTTP
- Manejan validación de entrada (`@Valid`)
- Delegan lógica al Service
- Retornan DTOs (no entidades)
- Logging de operaciones

**Endpoints:**
- `AlumnoController` → `/api/v1/alumnos`
- `AsignaturaController` → `/api/v1/asignaturas`
- `EvaluacionController` → `/api/v1/evaluaciones`

### 2. **Services** (`service/`)
- Contienen toda la lógica de negocio
- Manejan validaciones de integridad
- Orquestan operaciones complejas
- Transaccionalidad (`@Transactional`)
- Logging de operaciones

**GestionService:**
- 21 métodos para CRUD de 3 entidades
- Validaciones de referencia (FK)
- Excepciones personalizadas

### 3. **DTOs** (`dto/`)
- Objetos de transferencia de datos
- Aislamiento de API externa
- Validaciones Jakarta
- Campos read-only (ID)

**DTOs:**
- `AlumnoDTO` - RUT, nombre
- `AsignaturaDTO` - código, nombre, créditos
- `EvaluacionDTO` - calificación 1.0-7.0

### 4. **Entities** (`entity/`)
- Modelos JPA con `@Entity`
- Validaciones de BD (NOT NULL, UNIQUE)
- Anotaciones Lombok (`@Data`, `@Builder`)

**Entidades:**
- `Alumno` - id, nombre, rut
- `Asignatura` - id, nombre, código, créditos
- `Evaluacion` - id, alumnoId, asignaturaId, calificacion, fecha

### 5. **Repositories** (`repository/`)
- Interfaces que extienden `JpaRepository`
- Generan SQL automáticamente
- Soporte para queries personalizadas

**Repositories:**
- `AlumnoRepository`
- `AsignaturaRepository`
- `EvaluacionRepository`

### 6. **Mappers** (`mapper/`)
- Conversión Entity ↔ DTO
- Desacoplamiento de capas
- Reutilizable en toda la aplicación

**Mappers:**
- `AlumnoMapper`
- `AsignaturaMapper`
- `EvaluacionMapper`

### 7. **Exception Handling** (`exception/`)
- Excepciones personalizadas
- Handler global centralizado
- Respuestas JSON consistentes

**Componentes:**
- `ResourceNotFoundException` - 404
- `ValidationException` - 400
- `GlobalExceptionHandler` - Maneja todas las excepciones
- `ErrorResponse` - Formato de error estándar

---

## 🔄 Flujo de Solicitud Típica

```
1. Cliente envía HTTP Request
                ↓
2. Controller recibe y valida (@Valid)
                ↓
3. Controller inyecta Service
                ↓
4. Service ejecuta lógica de negocio
                ↓
5. Service inyecta Repository
                ↓
6. Repository ejecuta query a BD
                ↓
7. Resultado se mapea a DTO
                ↓
8. Controller retorna DTO con status HTTP
                ↓
9. GlobalExceptionHandler maneja errores
                ↓
10. Cliente recibe JSON Response
```

---

## ✨ Mejores Prácticas Implementadas

| Práctica | Implementación |
|----------|-----------------|
| **SRP** (Single Responsibility) | Cada clase tiene una única responsabilidad |
| **DIP** (Dependency Inversion) | Inyección de dependencias con Spring |
| **OCP** (Open/Closed) | Fácil agregar nuevas entidades |
| **DRY** (Don't Repeat Yourself) | Mappers y handlers centralizados |
| **Validación** | Multi-level (DTO, Entity, Service) |
| **Transaccionalidad** | Operaciones ACID |
| **Logging** | Trazabilidad completa |
| **Error Handling** | Respuestas predecibles |
| **API Versionado** | `/api/v1/` para futuras versiones |
| **RESTful** | Métodos HTTP estándares |

---

## 🛡️ Validaciones Implementadas

### A Nivel de DTO
```java
@NotBlank(message = "El nombre no puede estar vacío")
@Pattern(regexp = "^\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9kK]$")
@DecimalMin(value = "1.0")
@DecimalMax(value = "7.0")
```

### A Nivel de Entity
```java
@Column(nullable = false)
@Column(unique = true)
```

### A Nivel de Service
```java
if (!alumnoRepository.existsById(id)) {
    throw new ResourceNotFoundException(...);
}
```

---

## 📊 Estadísticas del Código

| Métrica | Valor |
|---------|-------|
| Clases Java | 21 |
| Interfaces | 3 (Repositories) |
| Métodos en Service | 21 |
| Endpoints REST | 15 |
| DTOs | 3 |
| Mappers | 3 |
| Excepciones Personalizadas | 2 |

---

## 🚀 Cómo Ejecutar

### Compilar
```bash
./mvnw clean compile
```

### Empaquetar
```bash
./mvnw clean package
```

### Ejecutar
```bash
./mvnw spring-boot:run
```

### Tests
```bash
./mvnw test
```

---

## 📝 Convenciones de Código

### Rutas API
- Base: `/api/v1/`
- Recursos plurales: `/alumnos`, `/asignaturas`
- Métodos HTTP: GET, POST, PUT, DELETE

### Nombrado
- Controllers: `*Controller`
- Services: `*Service`
- Repositories: `*Repository`
- DTOs: `*DTO`
- Mappers: `*Mapper`
- Excepciones: `*Exception`

### Logging
- Entrada de métodos: `log.info("GET /api/v1/...")`
- Éxito de operaciones: `log.info("... guardado exitosamente")`
- Advertencias: `log.warn("Error de validación...")`
- Errores: `log.error("Error inesperado", ex)`

---

## 🔐 Seguridad

- Validaciones en múltiples niveles
- Manejo centralizado de excepciones
- IDs read-only en DTOs
- Logs de auditoría
- Transacciones ACID

---

## 📈 Mejoras Futuras

- [ ] Tests unitarios (JUnit + Mockito)
- [ ] Tests de integración
- [ ] Swagger/OpenAPI Documentation
- [ ] Autenticación/Autorización (Spring Security)
- [ ] Paginación en listados
- [ ] Caché (Redis)
- [ ] Eventos de dominio
- [ ] Relaciones JPA (@ManyToOne)
- [ ] Auditoría de cambios (@CreatedDate, @LastModifiedBy)
- [ ] Búsqueda avanzada (Elasticsearch)

---

## 📚 Referencias

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Jakarta EE Validation](https://jakarta.ee/specifications/validation/)
- [Lombok Documentation](https://projectlombok.org/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [RESTful API Best Practices](https://restfulapi.net/)

---

**Versión:** 1.0  
**Fecha de Refactorización:** 2026-05-06  
**Estado:** ✅ Producción Lista
