package cl.colegio.ohiggins.gestion_academica.mapper;

import cl.colegio.ohiggins.gestion_academica.dto.EvaluacionDTO;
import cl.colegio.ohiggins.gestion_academica.entity.Evaluacion;
import org.springframework.stereotype.Component;

@Component
public class EvaluacionMapper {
    
    public EvaluacionDTO toDTO(Evaluacion evaluacion) {
        if (evaluacion == null) {
            return null;
        }
        return EvaluacionDTO.builder()
                .id(evaluacion.getId())
                .alumnoId(evaluacion.getAlumnoId())
                .asignaturaId(evaluacion.getAsignaturaId())
                .calificacion(evaluacion.getCalificacion())
                .fecha(evaluacion.getFecha())
                .build();
    }

    public Evaluacion toEntity(EvaluacionDTO dto) {
        if (dto == null) {
            return null;
        }
        return Evaluacion.builder()
                .id(dto.getId())
                .alumnoId(dto.getAlumnoId())
                .asignaturaId(dto.getAsignaturaId())
                .calificacion(dto.getCalificacion())
                .fecha(dto.getFecha())
                .build();
    }
}
