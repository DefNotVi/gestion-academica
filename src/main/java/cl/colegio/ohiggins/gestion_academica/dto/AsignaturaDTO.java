package cl.colegio.ohiggins.gestion_academica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "El nombre de la asignatura no puede estar vacío")
    private String nombre;

    @Min(value = 1, message = "El código debe ser mayor a 0")
    private Integer codigo;

    @Min(value = 1, message = "Los créditos deben ser mayores a 0")
    private Integer creditos;
}
