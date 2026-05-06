## API Testing Examples

### Crear Alumno
```bash
curl -X POST http://localhost:8080/api/v1/alumnos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Pérez",
    "rut": "12.345.678-9"
  }'
```

**Respuesta (201 Created):**
```json
{
  "id": 1,
  "nombre": "Juan Pérez",
  "rut": "12.345.678-9"
}
```

### Listar Alumnos
```bash
curl -X GET http://localhost:8080/api/v1/alumnos \
  -H "Content-Type: application/json"
```

### Obtener Alumno Específico
```bash
curl -X GET http://localhost:8080/api/v1/alumnos/1 \
  -H "Content-Type: application/json"
```

### Actualizar Alumno
```bash
curl -X PUT http://localhost:8080/api/v1/alumnos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan Carlos Pérez",
    "rut": "12.345.678-9"
  }'
```

### Eliminar Alumno
```bash
curl -X DELETE http://localhost:8080/api/v1/alumnos/1
```

---

## Crear Asignatura
```bash
curl -X POST http://localhost:8080/api/v1/asignaturas \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Matemáticas",
    "codigo": 101,
    "creditos": 3
  }'
```

---

## Crear Evaluación
```bash
curl -X POST http://localhost:8080/api/v1/evaluaciones \
  -H "Content-Type: application/json" \
  -d '{
    "alumnoId": 1,
    "asignaturaId": 1,
    "calificacion": 6.5,
    "fecha": "2026-05-06"
  }'
```

---

## Errores Esperados

### RUT Inválido
```bash
curl -X POST http://localhost:8080/api/v1/alumnos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan",
    "rut": "INVALID"
  }'
```

**Respuesta (400 Bad Request):**
```json
{
  "timestamp": "2026-05-06T21:30:51",
  "status": 400,
  "message": "Errores de validación",
  "validationErrors": {
    "rut": "El RUT debe estar en formato válido (ej: 12.345.678-9)"
  }
}
```

### Alumno No Encontrado
```bash
curl -X GET http://localhost:8080/api/v1/alumnos/999
```

**Respuesta (404 Not Found):**
```json
{
  "timestamp": "2026-05-06T21:30:51",
  "status": 404,
  "message": "Alumno no encontrado con ID: 999"
}
```

### Calificación Inválida
```bash
curl -X POST http://localhost:8080/api/v1/evaluaciones \
  -H "Content-Type: application/json" \
  -d '{
    "alumnoId": 1,
    "asignaturaId": 1,
    "calificacion": 10.0,
    "fecha": "2026-05-06"
  }'
```

**Respuesta (400 Bad Request):**
```json
{
  "timestamp": "2026-05-06T21:30:51",
  "status": 400,
  "message": "Errores de validación",
  "validationErrors": {
    "calificacion": "La calificación máxima es 7.0"
  }
}
```
