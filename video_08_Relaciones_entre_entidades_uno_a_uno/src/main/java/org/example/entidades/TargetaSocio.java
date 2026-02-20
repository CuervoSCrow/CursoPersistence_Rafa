package org.example.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "targetas_socios")
@Getter @Setter @NoArgsConstructor
public class TargetaSocio {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Solo se comenta en la parte 4.- Mismo Id
    private Integer id;

    @Column(nullable = false,length = 20,unique = true)
    private String numero;

    @Column(name="miembro_desde", nullable = false)
    private LocalDate miembroDesde;

    @Column(name="fecha_expiracion", nullable = false)
    private LocalDate fechaExpiracion;

    @Column(nullable = false)
    private boolean activa;

//    Unidireccional @OneToOne Cuando Socio es la entidad Padre
    /*
    public TargetaSocio(String numero) {
        this.numero = numero;
        this.miembroDesde = LocalDate.now();
        this.fechaExpiracion=LocalDate.now().plusYears(3);
        this.activa=true;
    }

     */

    //    Unidireccional @OneToOne Cuando TargetaSocio es la entidad Padre
    /*
    @OneToOne(optional = false)
    private Socio socio;

    public TargetaSocio(String numero,Socio socio) {
        this.numero = numero;
        this.miembroDesde = LocalDate.now();
        this.fechaExpiracion=LocalDate.now().plusYears(3);
        this.activa=true;
        this.socio = socio;
    }

     */

//        Parte 4 con el mismo Id
///*
     @OneToOne(optional = false)
     @MapsId
    private Socio socio;

    public TargetaSocio(String numero,Socio socio) {
        this.numero = numero;
        this.miembroDesde = LocalDate.now();
        this.fechaExpiracion=LocalDate.now().plusYears(3);
        this.activa=true;
        this.socio = socio;
    }
// */

}
