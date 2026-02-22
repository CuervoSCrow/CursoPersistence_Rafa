package org.example.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "salones")
@Getter @Setter @NoArgsConstructor
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="nombre",length = 30, nullable = false)
    private String nombre;

    @OneToMany
    @JoinColumn(name ="salon_id")
    List<Alumno> alumnos;

    public Salon(String nombre, List<Alumno> alumnos) {
        this.nombre = nombre;
        this.alumnos = alumnos;
    }
}
