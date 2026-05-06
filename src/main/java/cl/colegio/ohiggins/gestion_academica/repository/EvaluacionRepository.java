package cl.colegio.ohiggins.gestion_academica.repository;

import cl.colegio.ohiggins.gestion_academica.entity.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
}
