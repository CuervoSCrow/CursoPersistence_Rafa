package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EstadisticaDTO {
    private String nombreCurso;
    private Long numeroAlumnos;
    private Double promedio;
    private int notaMaxima;
    private int notaMinima;

    @Override
    public String toString() {
        return "EstadisticaDTO{" +
                "nombreCurso='" + nombreCurso + '\'' +
                ", numeroAlumnos=" + numeroAlumnos +
                ", promedio=" + promedio +
                ", notaMaxima=" + notaMaxima +
                ", notaMinima=" + notaMinima +
                '}';
    }
}
