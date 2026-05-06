package cl.colegio.ohiggins.gestion_academica.controller;

import cl.colegio.ohiggins.gestion_academica.dto.AlumnoDTO;
import cl.colegio.ohiggins.gestion_academica.service.GestionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alumnos")
@Slf4j
public class AlumnoController {

    @Autowired
    private GestionService gestionService;

    /**
     * Obtiene la lista de todos los alumnos
     * GET: /api/v1/alumnos
     */
    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> listarAlumnos() {
        log.info("GET /api/v1/alumnos - Listando todos los alumnos");
        List<AlumnoDTO> alumnos = gestionService.obtenerTodosAlumnos();
        return ResponseEntity.ok(alumnos);
    }

    /**
     * Obtiene un alumno específico por su ID
     * GET: /api/v1/alumnos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDTO> obtenerAlumno(@PathVariable Long id) {
        log.info("GET /api/v1/alumnos/{} - Obteniendo alumno", id);
        AlumnoDTO alumno = gestionService.obtenerAlumnoPorId(id);
        return ResponseEntity.ok(alumno);
    }

    /**
     * Crea un nuevo alumno
     * POST: /api/v1/alumnos
     */
    @PostMapping
    public ResponseEntity<AlumnoDTO> crearAlumno(@Valid @RequestBody AlumnoDTO alumnoDTO) {
        log.info("POST /api/v1/alumnos - Creando nuevo alumno con RUT: {}", alumnoDTO.getRut());
        AlumnoDTO nuevoAlumno = gestionService.guardarAlumno(alumnoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlumno);
    }

    /**
     * Actualiza un alumno existente
     * PUT: /api/v1/alumnos/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<AlumnoDTO> actualizarAlumno(
            @PathVariable Long id,
            @Valid @RequestBody AlumnoDTO alumnoDTO) {
        log.info("PUT /api/v1/alumnos/{} - Actualizando alumno", id);
        AlumnoDTO alumnoActualizado = gestionService.actualizarAlumno(id, alumnoDTO);
        return ResponseEntity.ok(alumnoActualizado);
    }

    /**
     * Elimina un alumno
     * DELETE: /api/v1/alumnos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id) {
        log.info("DELETE /api/v1/alumnos/{} - Eliminando alumno", id);
        gestionService.eliminarAlumno(id);
        return ResponseEntity.noContent().build();
    }
}