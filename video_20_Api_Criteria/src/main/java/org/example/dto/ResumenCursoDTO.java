package org.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ResumenCursoDTO {
    private Integer idCurso;
    private String nombreCurso;
    private int nivel;
    private String nombreAlumno;

    @Override
    public String toString() {
        return "ResumenCursoDTO{" +
                "idCurso=" + idCurso +
                ", nombreCurso='" + nombreCurso + '\'' +
                ", nivel=" + nivel +
                ", nombreAlumno='" + nombreAlumno + '\'' +
                '}';
    }
}
