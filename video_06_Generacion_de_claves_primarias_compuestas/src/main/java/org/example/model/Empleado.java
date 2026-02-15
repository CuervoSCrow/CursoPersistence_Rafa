package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "empleados",
        schema = "public",
        uniqueConstraints = {@UniqueConstraint(
                name = "telefono-unique",
                columnNames = {"telefono"})},
        indexes = {@Index(
                        columnList = "fecha_nacimiento",
                        unique = false)}
        )
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer codigo;

    @Column(name = "nombres", nullable = false, length = 60)
    private String nombre;

    @Column(name = "documento",nullable = false,length = 15)
    private String documento;

    @Column(name = "telefono",nullable = false,length = 20)
    private String telefono;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNac;

    @Formula("DATE PART('year',AGE(fecha_nacimiento))")
    private int edad;

    @Column(name = "tipo_empleado",nullable = false,
            columnDefinition = "SMALLINT default 1")
    private int tipo;

    @Column(name="salario",precision = 10, scale = 2)
    private BigDecimal salario;

    public Empleado(String nombre,
                    String documento,
                    String telefono,
                    LocalDate fechaNac,
                    int edad,
                    int tipo,
                    BigDecimal salario) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
        this.edad = edad;
        this.tipo = tipo;
        this.salario = salario;
    }
}
