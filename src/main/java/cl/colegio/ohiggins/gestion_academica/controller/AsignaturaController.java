package cl.colegio.ohiggins.gestion_academica.controller;

import cl.colegio.ohiggins.gestion_academica.dto.AsignaturaDTO;
import cl.colegio.ohiggins.gestion_academica.service.GestionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/asignaturas")
@Slf4j
public class AsignaturaController {

    @Autowired
    private GestionService gestionService;

    /**
     * Obtiene la lista de todas las asignaturas
     * GET: /api/v1/asignaturas
     */
    @GetMapping
    public ResponseEntity<List<AsignaturaDTO>> listarAsignaturas() {
        log.info("GET /api/v1/asignaturas - Listando todas las asignaturas");
        List<AsignaturaDTO> asignaturas = gestionService.obtenerTodasAsignaturas();
        return ResponseEntity.ok(asignaturas);
    }

    /**
     * Obtiene una asignatura específica por su ID
     * GET: /api/v1/asignaturas/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> obtenerAsignatura(@PathVariable Long id) {
        log.info("GET /api/v1/asignaturas/{} - Obteniendo asignatura", id);
        AsignaturaDTO asignatura = gestionService.obtenerAsignaturaPorId(id);
        return ResponseEntity.ok(asignatura);
    }

    /**
     * Crea una nueva asignatura
     * POST: /api/v1/asignaturas
     */
    @PostMapping
    public ResponseEntity<AsignaturaDTO> crearAsignatura(@Valid @RequestBody AsignaturaDTO asignaturaDTO) {
        log.info("POST /api/v1/asignaturas - Creando nueva asignatura: {}", asignaturaDTO.getNombre());
        AsignaturaDTO nuevaAsignatura = gestionService.guardarAsignatura(asignaturaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAsignatura);
    }

    /**
     * Actualiza una asignatura existente
     * PUT: /api/v1/asignaturas/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> actualizarAsignatura(
            @PathVariable Long id,
            @Valid @RequestBody AsignaturaDTO asignaturaDTO) {
        log.info("PUT /api/v1/asignaturas/{} - Actualizando asignatura", id);
        AsignaturaDTO asignaturaActualizada = gestionService.actualizarAsignatura(id, asignaturaDTO);
        return ResponseEntity.ok(asignaturaActualizada);
    }

    /**
     * Elimina una asignatura
     * DELETE: /api/v1/asignaturas/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsignatura(@PathVariable Long id) {
        log.info("DELETE /api/v1/asignaturas/{} - Eliminando asignatura", id);
        gestionService.eliminarAsignatura(id);
        return ResponseEntity.noContent().build();
    }
}
