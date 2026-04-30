package cl.colegio.ohiggins.gestion_academica.repository;

import cl.colegio.ohiggins.gestion_academica.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}