package emi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Categoria {
    private String nombre;
    private String descripion;
    private Rubro rubro;
}
