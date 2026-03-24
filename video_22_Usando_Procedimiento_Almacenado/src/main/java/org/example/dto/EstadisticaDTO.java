package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter @AllArgsConstructor
public class EstadisticaDTO {
    private String nombreCurso;
    private Long numeroAlumnos;
    private BigDecimal promedio;
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
