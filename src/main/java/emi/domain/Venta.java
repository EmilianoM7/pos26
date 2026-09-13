package emi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Venta {
    private Date fecha;
    private Cliente cliente;
    private Vendedor vendedor;
    private DetalleVenta detalleVentas;
    private Movimiento movimiento;
}
