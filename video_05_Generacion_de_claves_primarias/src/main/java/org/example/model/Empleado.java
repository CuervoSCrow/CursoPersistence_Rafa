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
        indexes = {@Index(columnList = "fecha_nacimiento",unique = false)}
)
@NoArgsConstructor @Getter @Setter @AllArgsConstructor
public class Empleado {
    @Id
//    1ra Estrategia IDENTITY
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    2da Estrategia SEQUENCE
//    @SequenceGenerator(name = "secuencia_empleado",
//            sequenceName="empleado_secuencia",
//            initialValue = 10,
//            allocationSize = 20)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    3ra Estrategia  TABLE
//    @TableGenerator(name = "empleado_seq", table = "tabla_secuencias")
//    @GeneratedValue(strategy = GenerationType.TABLE,generator = "empleado_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigo;
    @Column(name = "nombres",nullable = false,length = 60)
    private String nombre;
    @Column(name="documento",nullable = false,length = 15)
    private String documento;
    @Column(name = "telefono",nullable = false,length = 20)
    private String telefono;
    @Column(name="fecha_nacimiento",nullable = false)
    private LocalDate fechaNac;

    @Formula("DATE_PART('year',AGE(fecha_nacimiento))")
    private int edad;

    @Column(name = "tipo_empleado",nullable = false,columnDefinition = "SMALLINT default 1")
    private int tipo;

    @Column(name="salario",precision = 10,scale = 2)
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
