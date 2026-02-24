package org.example.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "nombre_Rol",
            length = 30,
            nullable = false,
            unique = true)
    private String nombreRol;
/*
    public Rol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

*/

///*
    @ManyToMany(mappedBy = "roles")
    private Set<Usuario> Usuarios;

    public Rol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "id=" + id +
                ", nombreRol='" + nombreRol + '\'' +
                '}';
    }

    //*/
}
