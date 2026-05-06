package cl.colegio.ohiggins.gestion_academica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "El ID del alumno no puede ser nulo")
    private Long alumnoId;

    @NotNull(message = "El ID de la asignatura no puede ser nulo")
    private Long asignaturaId;

    @NotNull(message = "La calificación no puede ser nula")
    @DecimalMin(value = "1.0", message = "La calificación mínima es 1.0")
    @DecimalMax(value = "7.0", message = "La calificación máxima es 7.0")
    private Double calificacion;

    private LocalDate fecha;
}
