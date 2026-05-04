package cl.colegio.ohiggins.gestion_academica.controller;



import cl.colegio.ohiggins.gestion_academica.entity.Alumno;

import cl.colegio.ohiggins.gestion_academica.repository.AlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



import java.util.List;



@RestController

@RequestMapping("/alumnos")

public class AlumnoController {



  @Autowired

  private AlumnoRepository alumnoRepository;



  // Este método responde al GET en http://localhost:8080/alumnos

  @GetMapping

  public List<Alumno> listarAlumnos() {

    return alumnoRepository.findAll();

  }



  // Este método permitie guardar alumnos (sirve para probar con el Postman)

  @PostMapping

  public Alumno guardarAlumno(@RequestBody Alumno alumno) {

    return alumnoRepository.save(alumno);

  }

}