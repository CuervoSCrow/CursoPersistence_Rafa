package org.example.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="alumnos")
@NamedQuery(
        name = "Alumno.findAll",
        query = "SELECT a FROM Alumno a ORDER BY a.nombre"
)
@Getter @Setter @NoArgsConstructor
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "nombre",length = 50,nullable = false)
    private String nombre;

    @Column(name = "fecha_nacimineto",nullable = false)
    private LocalDate fechaNac;

    @Column(name = "email_alumno",unique = true)
    private String email;

    @Column(name = "nota_alumno",nullable = false)
    private int nota;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso")
    private Curso curso;

    public Alumno(String nombre,
                  LocalDate fechaNac,
                  String email, int nota,
                  Curso curso) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.email = email;
        this.nota = nota;
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNac=" + fechaNac +
                ", email='" + email + '\'' +
                ", nota=" + nota +
                '}';
    }
}
