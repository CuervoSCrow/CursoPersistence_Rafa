package org.example.entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/*
// //Estrategia SINGLE_TABLE
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

 */

/*
//  Estrategia InheritanceType.JOINED

@Entity
@PrimaryKeyJoinColumn(name = "estudiante_id")
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
 */

///*
//  Estrategia InheritanceType.TABLE_PER_CLASS

@Entity
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
// */
