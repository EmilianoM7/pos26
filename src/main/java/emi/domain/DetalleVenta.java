package emi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetalleVenta {
    private int cantidad;
    private Double precioUnitario;
    private Producto producto;
}
