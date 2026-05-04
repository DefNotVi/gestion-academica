package cl.colegio.ohiggins.gestion_academica.entity;



import jakarta.persistence.*;

import lombok.Data;



@Data

@Entity

@Table(name = "asignatura")

public class Asignatura {



  @Id

  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;



  private String codigo; // ej: "MAT-101"

  private String nombre; // ej: "Matemáticas"

  private String docente;

}