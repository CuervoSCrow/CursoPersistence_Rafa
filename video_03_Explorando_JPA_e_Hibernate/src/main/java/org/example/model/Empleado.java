package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Empleado {
    @Id
    private Integer codigo;
    private String nombre;
    private LocalDate fechaNac;
    private Double salario;

    public Empleado(){}

    public Empleado(Integer codigo,
                    String nombre,
                    LocalDate fechaNac,
                    Double salario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", fechaNac=" + fechaNac +
                ", salario=" + salario +
                '}';
    }
}
