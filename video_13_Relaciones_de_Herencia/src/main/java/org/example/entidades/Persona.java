package org.example.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
//Estrategia SINGLE_TABLE
@Entity
@Table(name="personas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@Getter @Setter @NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }
}

 */
///*
// strategy = InheritanceType.JOINED
//    Para una jerarquia esta bien pero cuando la jerarquia es mas grande puede haber problemas

@Entity
@Table(name="personas")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }
}

// */

