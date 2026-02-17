package org.example.model.pkey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class LineaFacturaPK implements Serializable {
    @Column(name="numero_factura",length = 20)
    private String numeroFactura;

    @Column(name = "codigo_producto")
    private Integer codigoProducto;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LineaFacturaPK that = (LineaFacturaPK) o;
        return Objects.equals(numeroFactura, that.numeroFactura) && Objects.equals(codigoProducto, that.codigoProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroFactura, codigoProducto);
    }
}
