package org.example.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("P")
@Getter @Setter @NoArgsConstructor
public class Profesor extends Persona{
    private String departamento;
    private String telefono;

    public Profesor(
            String nombre,
            String departamento,
            String telefono){
        super(nombre);
        this.departamento=departamento;
        this.telefono=telefono;
    }
}
