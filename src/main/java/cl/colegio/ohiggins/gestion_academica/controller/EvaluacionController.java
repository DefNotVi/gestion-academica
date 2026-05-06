package cl.colegio.ohiggins.gestion_academica.controller;

import cl.colegio.ohiggins.gestion_academica.dto.EvaluacionDTO;
import cl.colegio.ohiggins.gestion_academica.service.GestionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@Slf4j
public class EvaluacionController {

    @Autowired
    private GestionService gestionService;

    /**
     * Obtiene la lista de todas las evaluaciones
     * GET: /api/v1/evaluaciones
     */
    @GetMapping
    public ResponseEntity<List<EvaluacionDTO>> listarEvaluaciones() {
        log.info("GET /api/v1/evaluaciones - Listando todas las evaluaciones");
        List<EvaluacionDTO> evaluaciones = gestionService.obtenerTodasEvaluaciones();
        return ResponseEntity.ok(evaluaciones);
    }

    /**
     * Obtiene una evaluación específica por su ID
     * GET: /api/v1/evaluaciones/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionDTO> obtenerEvaluacion(@PathVariable Long id) {
        log.info("GET /api/v1/evaluaciones/{} - Obteniendo evaluación", id);
        EvaluacionDTO evaluacion = gestionService.obtenerEvaluacionPorId(id);
        return ResponseEntity.ok(evaluacion);
    }

    /**
     * Crea una nueva evaluación
     * POST: /api/v1/evaluaciones
     */
    @PostMapping
    public ResponseEntity<EvaluacionDTO> crearEvaluacion(@Valid @RequestBody EvaluacionDTO evaluacionDTO) {
        log.info("POST /api/v1/evaluaciones - Creando nueva evaluación para alumno ID: {}", 
                 evaluacionDTO.getAlumnoId());
        EvaluacionDTO nuevaEvaluacion = gestionService.guardarEvaluacion(evaluacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEvaluacion);
    }

    /**
     * Actualiza una evaluación existente
     * PUT: /api/v1/evaluaciones/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<EvaluacionDTO> actualizarEvaluacion(
            @PathVariable Long id,
            @Valid @RequestBody EvaluacionDTO evaluacionDTO) {
        log.info("PUT /api/v1/evaluaciones/{} - Actualizando evaluación", id);
        EvaluacionDTO evaluacionActualizada = gestionService.actualizarEvaluacion(id, evaluacionDTO);
        return ResponseEntity.ok(evaluacionActualizada);
    }

    /**
     * Elimina una evaluación
     * DELETE: /api/v1/evaluaciones/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvaluacion(@PathVariable Long id) {
        log.info("DELETE /api/v1/evaluaciones/{} - Eliminando evaluación", id);
        gestionService.eliminarEvaluacion(id);
        return ResponseEntity.noContent().build();
    }
}
