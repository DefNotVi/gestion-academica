package cl.colegio.ohiggins.gestion_academica.controller;



import cl.colegio.ohiggins.gestion_academica.entity.Asignatura;

import cl.colegio.ohiggins.gestion_academica.repository.AsignaturaRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



import java.util.List;



@RestController

@RequestMapping("/asignaturas")

public class AsignaturaController {



  @Autowired

  private AsignaturaRepository asignaturaRepository;



  // GET http://localhost:8080/asignaturas

  @GetMapping

  public List<Asignatura> listarAsignaturas() {

    return asignaturaRepository.findAll();

  }



  // POST http://localhost:8080/asignaturas (probar desde Postman)

  @PostMapping

  public Asignatura guardarAsignatura(@RequestBody Asignatura asignatura) {

    return asignaturaRepository.save(asignatura);

}
}