package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.pkey.LineaFacturaPK;

import java.math.BigDecimal;

@Entity
@Table(name = "linea_factura_2")
@IdClass(LineaFacturaPK.class)
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class LineaFactura2 {
    @Id
    private String numeroFactura;

    @Id
    private Integer codigoProducto;

    @Column(name="cantidad", nullable = false)
    private int cantidad;

    @Column(name="precio_unitario",
            nullable = false,
            precision = 10,
            scale =2)
    private BigDecimal precioUnitario;

    @Column(name = "porcentaje_IVA",nullable = false)
    private int porcentajeIVA;

    @Column(name = "porcentaje_descuento",nullable = false)
    private int porcentajeDescuento;
}
