package cl.colegio.ohiggins.gestion_academica.service;

import cl.colegio.ohiggins.gestion_academica.dto.AlumnoDTO;
import cl.colegio.ohiggins.gestion_academica.dto.AsignaturaDTO;
import cl.colegio.ohiggins.gestion_academica.dto.EvaluacionDTO;
import cl.colegio.ohiggins.gestion_academica.entity.Alumno;
import cl.colegio.ohiggins.gestion_academica.entity.Asignatura;
import cl.colegio.ohiggins.gestion_academica.entity.Evaluacion;
import cl.colegio.ohiggins.gestion_academica.exception.ResourceNotFoundException;
import cl.colegio.ohiggins.gestion_academica.mapper.AlumnoMapper;
import cl.colegio.ohiggins.gestion_academica.repository.AlumnoRepository;
import cl.colegio.ohiggins.gestion_academica.repository.AsignaturaRepository;
import cl.colegio.ohiggins.gestion_academica.repository.EvaluacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class GestionService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private AlumnoMapper alumnoMapper;

    // --- LÓGICA DE ALUMNOS ---
    
    public List<AlumnoDTO> obtenerTodosAlumnos() {
        log.info("Obteniendo lista de todos los alumnos");
        return alumnoRepository.findAll()
                .stream()
                .map(alumnoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AlumnoDTO obtenerAlumnoPorId(Long id) {
        log.info("Obteniendo alumno con ID: {}", id);
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado con ID: " + id));
        return alumnoMapper.toDTO(alumno);
    }

    public AlumnoDTO guardarAlumno(AlumnoDTO alumnoDTO) {
        log.info("Guardando nuevo alumno con RUT: {}", alumnoDTO.getRut());
        Alumno alumno = alumnoMapper.toEntity(alumnoDTO);
        Alumno savedAlumno = alumnoRepository.save(alumno);
        log.info("Alumno guardado exitosamente con ID: {}", savedAlumno.getId());
        return alumnoMapper.toDTO(savedAlumno);
    }

    public AlumnoDTO actualizarAlumno(Long id, AlumnoDTO alumnoDTO) {
        log.info("Actualizando alumno con ID: {}", id);
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alumno no encontrado con ID: " + id));
        
        alumno.setNombre(alumnoDTO.getNombre());
        alumno.setRut(alumnoDTO.getRut());
        
        Alumno updatedAlumno = alumnoRepository.save(alumno);
        log.info("Alumno actualizado exitosamente");
        return alumnoMapper.toDTO(updatedAlumno);
    }

    public void eliminarAlumno(Long id) {
        log.info("Eliminando alumno con ID: {}", id);
        if (!alumnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Alumno no encontrado con ID: " + id);
        }
        alumnoRepository.deleteById(id);
        log.info("Alumno eliminado exitosamente");
    }

    // --- LÓGICA DE ASIGNATURAS ---
    
    public List<AsignaturaDTO> obtenerTodasAsignaturas() {
        log.info("Obteniendo lista de todas las asignaturas");
        return asignaturaRepository.findAll()
                .stream()
                .map(this::toAsignaturaDTO)
                .collect(Collectors.toList());
    }

    public AsignaturaDTO obtenerAsignaturaPorId(Long id) {
        log.info("Obteniendo asignatura con ID: {}", id);
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada con ID: " + id));
        return toAsignaturaDTO(asignatura);
    }

    public AsignaturaDTO guardarAsignatura(AsignaturaDTO asignaturaDTO) {
        log.info("Guardando nueva asignatura: {}", asignaturaDTO.getNombre());
        Asignatura asignatura = toAsignaturaEntity(asignaturaDTO);
        Asignatura savedAsignatura = asignaturaRepository.save(asignatura);
        log.info("Asignatura guardada exitosamente con ID: {}", savedAsignatura.getId());
        return toAsignaturaDTO(savedAsignatura);
    }

    public AsignaturaDTO actualizarAsignatura(Long id, AsignaturaDTO asignaturaDTO) {
        log.info("Actualizando asignatura con ID: {}", id);
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada con ID: " + id));
        
        asignatura.setNombre(asignaturaDTO.getNombre());
        asignatura.setCodigo(asignaturaDTO.getCodigo());
        asignatura.setCreditos(asignaturaDTO.getCreditos());
        
        Asignatura updatedAsignatura = asignaturaRepository.save(asignatura);
        log.info("Asignatura actualizada exitosamente");
        return toAsignaturaDTO(updatedAsignatura);
    }

    public void eliminarAsignatura(Long id) {
        log.info("Eliminando asignatura con ID: {}", id);
        if (!asignaturaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Asignatura no encontrada con ID: " + id);
        }
        asignaturaRepository.deleteById(id);
        log.info("Asignatura eliminada exitosamente");
    }

    // --- LÓGICA DE EVALUACIONES ---
    
    public List<EvaluacionDTO> obtenerTodasEvaluaciones() {
        log.info("Obteniendo lista de todas las evaluaciones");
        return evaluacionRepository.findAll()
                .stream()
                .map(this::toEvaluacionDTO)
                .collect(Collectors.toList());
    }

    public EvaluacionDTO obtenerEvaluacionPorId(Long id) {
        log.info("Obteniendo evaluación con ID: {}", id);
        Evaluacion evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluación no encontrada con ID: " + id));
        return toEvaluacionDTO(evaluacion);
    }

    public EvaluacionDTO guardarEvaluacion(EvaluacionDTO evaluacionDTO) {
        log.info("Guardando nueva evaluación para alumno ID: {} en asignatura ID: {}", 
                 evaluacionDTO.getAlumnoId(), evaluacionDTO.getAsignaturaId());
        
        // Validar que el alumno existe
        if (!alumnoRepository.existsById(evaluacionDTO.getAlumnoId())) {
            throw new ResourceNotFoundException("Alumno no encontrado con ID: " + evaluacionDTO.getAlumnoId());
        }
        
        // Validar que la asignatura existe
        if (!asignaturaRepository.existsById(evaluacionDTO.getAsignaturaId())) {
            throw new ResourceNotFoundException("Asignatura no encontrada con ID: " + evaluacionDTO.getAsignaturaId());
        }
        
        Evaluacion evaluacion = toEvaluacionEntity(evaluacionDTO);
        Evaluacion savedEvaluacion = evaluacionRepository.save(evaluacion);
        log.info("Evaluación guardada exitosamente con ID: {}", savedEvaluacion.getId());
        return toEvaluacionDTO(savedEvaluacion);
    }

    public EvaluacionDTO actualizarEvaluacion(Long id, EvaluacionDTO evaluacionDTO) {
        log.info("Actualizando evaluación con ID: {}", id);
        Evaluacion evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluación no encontrada con ID: " + id));
        
        // Validar que el alumno existe
        if (!alumnoRepository.existsById(evaluacionDTO.getAlumnoId())) {
            throw new ResourceNotFoundException("Alumno no encontrado con ID: " + evaluacionDTO.getAlumnoId());
        }
        
        // Validar que la asignatura existe
        if (!asignaturaRepository.existsById(evaluacionDTO.getAsignaturaId())) {
            throw new ResourceNotFoundException("Asignatura no encontrada con ID: " + evaluacionDTO.getAsignaturaId());
        }
        
        evaluacion.setAlumnoId(evaluacionDTO.getAlumnoId());
        evaluacion.setAsignaturaId(evaluacionDTO.getAsignaturaId());
        evaluacion.setCalificacion(evaluacionDTO.getCalificacion());
        evaluacion.setFecha(evaluacionDTO.getFecha());
        
        Evaluacion updatedEvaluacion = evaluacionRepository.save(evaluacion);
        log.info("Evaluación actualizada exitosamente");
        return toEvaluacionDTO(updatedEvaluacion);
    }

    public void eliminarEvaluacion(Long id) {
        log.info("Eliminando evaluación con ID: {}", id);
        if (!evaluacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Evaluación no encontrada con ID: " + id);
        }
        evaluacionRepository.deleteById(id);
        log.info("Evaluación eliminada exitosamente");
    }

    // --- MAPPERS AUXILIARES ---
    
    private AsignaturaDTO toAsignaturaDTO(Asignatura asignatura) {
        return AsignaturaDTO.builder()
                .id(asignatura.getId())
                .nombre(asignatura.getNombre())
                .codigo(asignatura.getCodigo())
                .creditos(asignatura.getCreditos())
                .build();
    }

    private Asignatura toAsignaturaEntity(AsignaturaDTO dto) {
        return Asignatura.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .codigo(dto.getCodigo())
                .creditos(dto.getCreditos())
                .build();
    }

    private EvaluacionDTO toEvaluacionDTO(Evaluacion evaluacion) {
        return EvaluacionDTO.builder()
                .id(evaluacion.getId())
                .alumnoId(evaluacion.getAlumnoId())
                .asignaturaId(evaluacion.getAsignaturaId())
                .calificacion(evaluacion.getCalificacion())
                .fecha(evaluacion.getFecha())
                .build();
    }

    private Evaluacion toEvaluacionEntity(EvaluacionDTO dto) {
        return Evaluacion.builder()
                .id(dto.getId())
                .alumnoId(dto.getAlumnoId())
                .asignaturaId(dto.getAsignaturaId())
                .calificacion(dto.getCalificacion())
                .fecha(dto.getFecha())
                .build();
    }
}