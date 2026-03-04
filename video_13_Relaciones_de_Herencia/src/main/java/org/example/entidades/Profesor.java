package org.example.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
// //Estrategia SINGLE_TABLE
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

 */

/*

//  Estrategia InheritanceType.JOINED
@Entity
@PrimaryKeyJoinColumn(name="profesor_id")
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
 */


///*

//  Estrategia InheritanceType.TABLE_PER_CLASS
@Entity
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
// */
