package cl.colegio.ohiggins.gestion_academica.mapper;

import cl.colegio.ohiggins.gestion_academica.dto.AsignaturaDTO;
import cl.colegio.ohiggins.gestion_academica.entity.Asignatura;
import org.springframework.stereotype.Component;

@Component
public class AsignaturaMapper {
    
    public AsignaturaDTO toDTO(Asignatura asignatura) {
        if (asignatura == null) {
            return null;
        }
        return AsignaturaDTO.builder()
                .id(asignatura.getId())
                .nombre(asignatura.getNombre())
                .codigo(asignatura.getCodigo())
                .creditos(asignatura.getCreditos())
                .build();
    }

    public Asignatura toEntity(AsignaturaDTO dto) {
        if (dto == null) {
            return null;
        }
        return Asignatura.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .codigo(dto.getCodigo())
                .creditos(dto.getCreditos())
                .build();
    }
}
