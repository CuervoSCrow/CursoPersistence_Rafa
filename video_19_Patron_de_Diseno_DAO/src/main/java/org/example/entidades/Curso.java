package org.example.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "nombre",nullable = false,length = 50)
    private String nombre;

    @Column(name = "nivel", nullable = false)
    private int nivel;

    @Column(name = "profesor", length = 50, nullable = false)
    private String profesor;

    @OneToMany(mappedBy = "curso",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL//,
//                orphanRemoval = true
    )
    private List<Alumno> alumnos;

    public Curso(String nombre, int nivel, String profesor) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nivel=" + nivel +
                ", profesor='" + profesor + '\'' +
                '}';
    }
}