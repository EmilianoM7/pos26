package emi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cliente {
    private String nombre;
    private String apellido;
    private Double saldo;
    private Movimiento[] movimientos;
}
