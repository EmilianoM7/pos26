package emi.front;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;

public class MainMenu extends JFrame {

    private int ancho = 250, alto= 350;

    public MainMenu(){
        setTitle("POS_26");
        setLayout(new BorderLayout(0, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ancho,alto);
        setMinimumSize(new DimensionUIResource(ancho,alto));
        setLocationRelativeTo(null);
        setContentPane(panelOpciones());
        setVisible(true);
    }

    private JPanel panelOpciones() {
        JPanel panel = GeneradorUI.nuevoPanelGrid(15,15);

        // ventas
        JPanel ventas = GeneradorUI.nuevoPanelBorder(0,0);
        ventas.add(GeneradorUI.nuevoButton("Nueva Venta",null, e -> {
            GeneradorUI.setUpFrame(new Ventana_RegistrarVenta(), this);
        }), BorderLayout.CENTER);
        ventas.add(GeneradorUI.nuevoButton("H",null, null),BorderLayout.EAST);
        // compras
        JPanel compras = GeneradorUI.nuevoPanelBorder(0,0);
        compras.add(GeneradorUI.nuevoButton("Nueva Compra",null, null));
        compras.add(GeneradorUI.nuevoButton("H",null, null),BorderLayout.EAST);

        // panel Main
        panel.add(ventas);
        panel.add(compras);
        panel.add(GeneradorUI.nuevoButton("Productos",null, e -> {
            GeneradorUI.setUpFrame(new Ventana_ListarItems(), this);
        }));

        panel.add(GeneradorUI.nuevoButton("Clientes",null, null));
        panel.add(GeneradorUI.nuevoButton("Proveedores",null, null));
        return panel;
    }
}
