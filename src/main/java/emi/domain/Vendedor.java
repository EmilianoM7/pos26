package emi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Vendedor {
    private String nombre;
    private String apellido;
    private Date fechaIngreso;
}
