package cl.colegio.ohiggins.gestion_academica.mapper;

import cl.colegio.ohiggins.gestion_academica.dto.AlumnoDTO;
import cl.colegio.ohiggins.gestion_academica.entity.Alumno;
import org.springframework.stereotype.Component;

@Component
public class AlumnoMapper {
    
    public AlumnoDTO toDTO(Alumno alumno) {
        if (alumno == null) {
            return null;
        }
        return AlumnoDTO.builder()
                .id(alumno.getId())
                .nombre(alumno.getNombre())
                .rut(alumno.getRut())
                .build();
    }

    public Alumno toEntity(AlumnoDTO dto) {
        if (dto == null) {
            return null;
        }
        return Alumno.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .rut(dto.getRut())
                .build();
    }
}
