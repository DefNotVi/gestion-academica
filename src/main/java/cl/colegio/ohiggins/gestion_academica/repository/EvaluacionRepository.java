package cl.colegio.ohiggins.gestion_academica.repository;



import cl.colegio.ohiggins.gestion_academica.entity.Evaluacion;

import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;



public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {



  // Consultas útiles para tu dominio académico

  List<Evaluacion> findByAlumnoId(Long alumnoId);

  List<Evaluacion> findByAsignaturaId(Long asignaturaId);

}