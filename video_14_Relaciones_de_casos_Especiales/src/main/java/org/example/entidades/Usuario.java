package org.example.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.logging.Level;

@Entity
@Table(name = "usuarios")
@SecondaryTable(name = "contacto_usuarios",
                pkJoinColumns = @PrimaryKeyJoinColumn(name="usuario_id"))
@SecondaryTable(name = "preferencias_usuarios",
                pkJoinColumns = @PrimaryKeyJoinColumn(name = "usuario_id"))
@Getter @Setter @NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 50,nullable = false)
    private String nombre;

    @Column(length = 255,nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(table = "contacto_usuarios",nullable = false)
    private String direccion;

    @Column(table = "contacto_usuarios",length = 20,nullable = false)
    private String telefono;

    @Column(table="preferencias_usuarios",length = 20,nullable = false)
    private String color;

    @Column(table= "preferencias_usuarios",length = 20, nullable = false)
    private String tema;

    public Usuario(String nombre,
                   String email,
                   String password,
                   String direccion,
                   String telefono,
                   String color,
                   String tema) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.color = color;
        this.tema = tema;
    }
}
