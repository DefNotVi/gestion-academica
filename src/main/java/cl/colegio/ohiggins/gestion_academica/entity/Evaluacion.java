package cl.colegio.ohiggins.gestion_academica.entity;



import jakarta.persistence.*;

import lombok.Data;



import java.time.LocalDate;



@Data

@Entity

@Table(name = "evaluacion")

public class Evaluacion {



  @Id

  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;



  @ManyToOne(fetch = FetchType.LAZY)

  @JoinColumn(name = "alumno_id", nullable = false)

  private Alumno alumno;



  @ManyToOne(fetch = FetchType.LAZY)

  @JoinColumn(name = "asignatura_id", nullable = false)

  private Asignatura asignatura;



  private String tipo;    // ej: "Prueba", "Tarea", "Examen"

  private Double nota;    // 1.0 - 7.0

  private LocalDate fecha;

}