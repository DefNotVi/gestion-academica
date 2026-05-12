package cl.colegio.ohiggins.gestion_academica.entity;



import jakarta.persistence.*;

import lombok.Data;



import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Data

@Entity

@Table(name = "evaluacion")

public class Evaluacion {



  @Id

  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;


  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "alumno_id", nullable = false)
  private Alumno alumno;



  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "asignatura_id", nullable = false)
  private Asignatura asignatura;



  private String tipo;    // ej: "Prueba", "Tarea", "Examen"

  private Double nota;    // 1.0 - 7.0

  private LocalDate fecha;

}