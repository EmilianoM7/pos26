package emi.front;

import emi.domain.TipoRegistro;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Ventana_RegistrarVenta extends JFrame {

    private Double total = 0.0;

    public Ventana_RegistrarVenta(){
        setLayout(new BorderLayout(0, 10));
        setSize(700,500);//250,250);
        setMinimumSize(new DimensionUIResource(250,250));
        setContentPane(panelPrincipal());

        setVisible(true);
    }

    private JPanel panelPrincipal() {
        JPanel panel = GeneradorUI.nuevoPanelBorder(15,15);

        panel.add(panelCliente(),BorderLayout.NORTH);
        panel.add(panelCompra(),BorderLayout.CENTER);

        return panel;
    }

    private JPanel panelCliente(){
        JPanel panel = GeneradorUI.nuevoPanelBorder(0,0);

        JTextField dniCliente = GeneradorUI.nuevoTextField("Dni_cliente",100);
        JLabel nombreCliente = GeneradorUI.nuevoLabel("Raul...", 2);

        JPanel pnlWest = GeneradorUI.nuevoPanelHorizontal(false,0,0);
        pnlWest.add(GeneradorUI.nuevoLabel("Cliente: ", 1));
        pnlWest.add(dniCliente);

        panel.add(pnlWest,BorderLayout.WEST);
        panel.add(nombreCliente, BorderLayout.CENTER);
        panel.add(GeneradorUI.nuevoButton("Registrar Cliente",null,null), BorderLayout.EAST);

        return panel;
    }

    private JPanel panelCompra(){
        JPanel panel = GeneradorUI.nuevoPanelBorder(0,0);
        // center - tabla
        String[] columnas = {"Cant", "ID", "Nombre", "Precio_vta", "subtotal"};
        JTable tabla = GeneradorUI.nuevaTabla(columnas,null,false, TipoRegistro.ITEM);
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

        // swouth - botones - total
        JPanel pnlSouth = GeneradorUI.nuevoPanelVertical(0,0);

        JPanel pnlBotones = GeneradorUI.nuevoPanelHorizontal(false,0,0);
        pnlBotones.add(GeneradorUI.nuevoButton("+",null,null));
        pnlBotones.add(GeneradorUI.nuevoButton("-",null,null));
        pnlBotones.add(GeneradorUI.nuevoButton("x",null,null));

        JPanel pnlTotal = GeneradorUI.nuevoPanelHorizontal(true,0,0);
        pnlTotal.add(GeneradorUI.nuevoLabel("Total: $",1));
        this.total = 72990.00;
        pnlTotal.add(GeneradorUI.nuevoLabel("" + this.total,0));

        JPanel pnlBtnTotal = GeneradorUI.nuevoPanelBorder(0,0);
        pnlBtnTotal.add(pnlBotones, BorderLayout.WEST);
        pnlBtnTotal.add(pnlTotal, BorderLayout.EAST);

        JPanel pnlRegistrar = GeneradorUI.nuevoPanelHorizontal(true,0,0);
        pnlRegistrar.add(GeneradorUI.nuevoButton("Registrar Venta",null,e -> {
            confirmarVenta();
        }));

        pnlSouth.add(pnlBtnTotal);
        pnlSouth.add(GeneradorUI.nuevoSeparador(false,8));
        pnlSouth.add(pnlRegistrar);

        // panel
        panel.add(new JScrollPane(tabla),BorderLayout.CENTER);
        panel.add(pnlSouth,BorderLayout.SOUTH);

        return panel;
    }

    private void confirmarVenta(){
        JPanel detalle = GeneradorUI.nuevoPanelVertical(0,0);
        detalle.add(GeneradorUI.nuevoLabelHorizontal("Total: $" + this.total, 1));
        String[] formas = {"Efectivo","Debito"};
        detalle.add(GeneradorUI.nuevoPanelRadio("Forma de Pago", formas, false));

        JScrollPane scroll =  new JScrollPane(detalle);
        //scroll.setPreferredSize(new Dimension(100, 200));
        // pedir confirmacion
        if (
            JOptionPane.showConfirmDialog(null,
                    detalle,
                    "¿Confirmar cambios?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            ) == JOptionPane.YES_NO_OPTION
        ){
            // TODO
            //Backend.editarVenta();
        }
    }

}
