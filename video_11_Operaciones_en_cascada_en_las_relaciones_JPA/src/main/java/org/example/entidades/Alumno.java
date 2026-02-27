package org.example.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Length;

import java.time.LocalDate;

@Entity
@Table(name = "alumnos")
@Getter @Setter @NoArgsConstructor
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "nombre",length = 50,nullable = false)
    private String nombre;

    @Column(name="fecha_nacimiento",nullable = false)
    private LocalDate fechaNac;

    @Column(name="email",length = 255,nullable = false,unique = true)
    private String email;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_curso")
    private Curso curso;

    public Alumno(String nombre,
                  LocalDate fechaNac,
                  String email,
                  Curso curso) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.email = email;
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", fechaNac=" + fechaNac +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
