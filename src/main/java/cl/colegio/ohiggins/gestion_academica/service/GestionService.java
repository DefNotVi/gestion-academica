package cl.colegio.ohiggins.gestion_academica.service;

import cl.colegio.ohiggins.gestion_academica.entity.*;
import cl.colegio.ohiggins.gestion_academica.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GestionService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    // --- LÓGICA DE ALUMNOS ---
    public List<Alumno> obtenerTodosAlumnos() { return alumnoRepository.findAll(); }
    public Alumno guardarAlumno(Alumno a) { return alumnoRepository.save(a); }

    // --- LÓGICA DE ASIGNATURAS (Para al que le toque) ---

    // --- LÓGICA DE EVALUACIONES (Para al que le toque) ---
}