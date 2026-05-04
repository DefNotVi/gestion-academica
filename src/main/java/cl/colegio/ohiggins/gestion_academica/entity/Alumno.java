package cl.colegio.ohiggins.gestion_academica.entity;



import jakarta.persistence.*;

import lombok.Data;



@Data

@Entity

@Table(name = "alumno")

public class Alumno {



  @Id

  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private Long id;



  private String rut;

  private String nombre;

  private String apellido;

  private String curso; // ej: "1°A", "2°B"

  private String email;

}