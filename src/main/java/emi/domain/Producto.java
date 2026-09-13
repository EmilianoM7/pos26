package emi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Producto {
    private String id;
    private String nombre;
    private String descripion;
    private Categoria categoria;
    private Marca marca;
    private Double precioVenta;
    private Double precioCompra;
}
