package org.example.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "socios")
@Getter @Setter @NoArgsConstructor
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="nombre",length = 50,nullable = false)
    private String nombre;

    @Column(name="numero_documento",length = 20,nullable = false,unique = true)
    private String nroDocumento;

    @Column(name="fecha_nacimineto",nullable = false)
    private LocalDate fechaNac;

    // Unidireccional @OneToOne cuando Socio es la Entidad Padre
    /*
    @OneToOne(optional = false)
    TargetaSocio targetaSocio;

    public Socio(String nombre,
                 String nroDocumento,
                 LocalDate fechaNac,
                 TargetaSocio targetaSocio) {
        this.nombre = nombre;
        this.nroDocumento = nroDocumento;
        this.fechaNac = fechaNac;
        this.targetaSocio=targetaSocio;
    }
    */

    // Unidireccional @OneToOne cuando Targeta Socio es la Entidad Padre
/*
    public Socio(String nombre,
                 String nroDocumento,
                 LocalDate fechaNac) {
        this.nombre = nombre;
        this.nroDocumento = nroDocumento;
        this.fechaNac = fechaNac;
    }
 */
    // Bidireccional
/*
    @OneToOne(mappedBy = "socio")
    TargetaSocio targetaSocio;

    public Socio(String nombre,
                 String nroDocumento,
                 LocalDate fechaNac) {
        this.nombre = nombre;
        this.nroDocumento = nroDocumento;
        this.fechaNac = fechaNac;
    }

    @Override
    public String toString() {
        return "Socio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nroDocumento='" + nroDocumento + '\'' +
                ", fechaNac=" + fechaNac +
                ", targetaSocio=" + targetaSocio +
                '}';
    }

    */

///*
    public Socio(String nombre,
                 String nroDocumento,
                 LocalDate fechaNac) {
        this.nombre = nombre;
        this.nroDocumento = nroDocumento;
        this.fechaNac = fechaNac;
    }
//*/
}
