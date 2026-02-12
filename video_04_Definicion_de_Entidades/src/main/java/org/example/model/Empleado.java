package org.example.model;

import jakarta.persistence.*;
import java.time.Period;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "empleados",
        schema = "persistencia",
        uniqueConstraints = {@UniqueConstraint(name="telefono_unique",columnNames = {"telefono"})},
        indexes = {@Index(columnList = "fecha_nacimiento",unique = true)}
)
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Empleado {
    @Id
    private Integer codigo;
    @Column(name = "nombres",nullable = false,length = 60)
    private String nombre;
    @Column(name="documento",nullable = false,length = 15)
    @NaturalId(mutable = false)
    private String documento;
    @Column(name="telefono",nullable = false,length = 20)
    private String telefono;
    @Column(name = "fecha_nacimiento",nullable = false)
    private LocalDate fechaNac;

//    @Transient
//    private int edad;
    @Formula("DATE_PART('year',AGE(fecha_nacimiento))")
    private int edad2;

    @Column(name="tipo_empleado",nullable = false,columnDefinition = "SMALLINT default 1")
    private int tipo;
    @Column(name = "salario",precision = 10,scale = 2)
    private BigDecimal salario;

    public int calcularEdad(){
        Period period = Period.between(fechaNac, LocalDate.now());
        return period.getYears();
    }

}
