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
    private double salario;
    
    public Empleado(){}

    public Empleado(Integer codigo,
                    String nombre,
                    LocalDate fechaNac,
                    double salario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.salario = salario;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
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
