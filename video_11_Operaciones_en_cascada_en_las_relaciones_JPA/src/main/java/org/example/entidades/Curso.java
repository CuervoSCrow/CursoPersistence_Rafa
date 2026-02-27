package org.example.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "Cursos")
@Getter @Setter @NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer Id;

    @Column(name = "nombre",length = 50,nullable = false)
    private String nombre;

    @Column(name = "nivel",nullable = false)
    private int nivel;

    @Column(name = "profesor", length = 50,nullable = false)
    private String profesor;

    //    /* Aquí se convierte en un nrelación Bidireccional
    @OnDelete(action= OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "curso",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Alumno> alumnos;
//    */


    public Curso(String profesor, int nivel, String nombre) {
        this.nombre = nombre;
        this.profesor = profesor;
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", nivel=" + nivel +
                ", profesor='" + profesor + '\'' +
                '}';
    }
}
