package org.example.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("E")
@Getter @Setter @NoArgsConstructor
public class Estudiante extends Persona {
    private String matricula;

    private LocalDate fechaNac;

    public Estudiante(String nombre,
                      String matricula,
                      LocalDate fechaNac) {
        super(nombre);
        this.matricula = matricula;
        this.fechaNac = fechaNac;
    }
}
