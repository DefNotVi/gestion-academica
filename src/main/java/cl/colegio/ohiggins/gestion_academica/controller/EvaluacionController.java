package cl.colegio.ohiggins.gestion_academica.controller;



import cl.colegio.ohiggins.gestion_academica.entity.Evaluacion;

import cl.colegio.ohiggins.gestion_academica.repository.EvaluacionRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



import java.util.List;



@RestController

@RequestMapping("/evaluaciones")

public class EvaluacionController {



  @Autowired

  private EvaluacionRepository evaluacionRepository;



  // GET http://localhost:8080/evaluaciones

  @GetMapping

  public List<Evaluacion> listarEvaluaciones() {

    return evaluacionRepository.findAll();

  }



  // POST http://localhost:8080/evaluaciones

  @PostMapping

  public Evaluacion guardarEvaluacion(@RequestBody Evaluacion evaluacion) {

    return evaluacionRepository.save(evaluacion);

  }



  // GET http://localhost:8080/evaluaciones/alumno/1

  @GetMapping("/alumno/{alumnoId}")

  public List<Evaluacion> evaluacionesPorAlumno(@PathVariable Long alumnoId) {

    return evaluacionRepository.findByAlumnoId(alumnoId);

  }



  // GET http://localhost:8080/evaluaciones/asignatura/1

  @GetMapping("/asignatura/{asignaturaId}")

  public List<Evaluacion> evaluacionesPorAsignatura(@PathVariable Long asignaturaId) {

    return evaluacionRepository.findByAsignaturaId(asignaturaId);

  }
}