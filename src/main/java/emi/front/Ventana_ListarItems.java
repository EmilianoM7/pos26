package emi.front;

import emi.back.Backend;
import emi.domain.TipoRegistro;
import emi.domain.Producto;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class Ventana_ListarItems extends JFrame {
    private TableRowSorter<DefaultTableModel> sorter;
    private JTextField campoBusqueda;

    public Ventana_ListarItems(){
        setLayout(new BorderLayout(0, 10));
        setSize(700,500);//250,250);
        setMinimumSize(new DimensionUIResource(250,250));
        setContentPane(panelPrincipal());

        setVisible(true);
    }

    private JTabbedPane panelPrincipal() {
        JTabbedPane tabbedPane = new JTabbedPane();
        //prod
        JPanel pnl_productos = GeneradorUI.nuevoPanelBorder(15,15);
        pnl_productos.add(panelBusqueda(TipoRegistro.ITEM),BorderLayout.NORTH);
        pnl_productos.add(panelTabla(TipoRegistro.ITEM),BorderLayout.CENTER);
        //cats
        JPanel pnl_cats = GeneradorUI.nuevoPanelBorder(15,15);
        pnl_productos.add(panelBusqueda(TipoRegistro.CATEGORIA),BorderLayout.NORTH);
        pnl_productos.add(panelTabla(TipoRegistro.CATEGORIA),BorderLayout.CENTER);
        //rubs
        JPanel pnl_rubros = GeneradorUI.nuevoPanelBorder(15,15);
        pnl_productos.add(panelBusqueda(TipoRegistro.RUBRO),BorderLayout.NORTH);
        pnl_productos.add(panelTabla(TipoRegistro.RUBRO),BorderLayout.CENTER);
        //marcas
        JPanel pnl_marcas = GeneradorUI.nuevoPanelBorder(15,15);
        pnl_productos.add(panelBusqueda(TipoRegistro.MARCA),BorderLayout.NORTH);
        pnl_productos.add(panelTabla(TipoRegistro.MARCA),BorderLayout.CENTER);

        // TABS
        tabbedPane.addTab("Productos",pnl_productos);
        tabbedPane.addTab("Categorias",pnl_cats);
        tabbedPane.addTab("Rubros",pnl_rubros);
        tabbedPane.addTab("Marcas",pnl_marcas);
        return tabbedPane;
    }

    private JPanel panelBusqueda(TipoRegistro tipo){
        JPanel panel = GeneradorUI.nuevoPanelVertical(0,0);

        this.campoBusqueda = GeneradorUI.nuevoTextField("Buscar...",300);
        // Filtro en tiempo real al escribir

        campoBusqueda.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e)  { filtrar(); }
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e)  { filtrar(); }
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filtrar(); }
        });

        JPanel pnlBusqueda = GeneradorUI.nuevoPanelHorizontal(false,0,0);
        pnlBusqueda.add(GeneradorUI.nuevoLabel("Productos",1));
        pnlBusqueda.add(campoBusqueda);
        pnlBusqueda.add(GeneradorUI.nuevoButton("+ Nuevo",null,null));

        panel.add(pnlBusqueda);
        panel.add(GeneradorUI.nuevoSeparador(false,8));

        // FILTROS solo para item
        System.out.println(tipo);
        if (tipo == TipoRegistro.ITEM){
            JPanel pnlFiltros = GeneradorUI.nuevoPanelHorizontal(false,0,0);
            pnlFiltros.add(GeneradorUI.nuevoPanelFiltro("Marcas", Backend.cargarMarcas(),true));
            pnlFiltros.add(GeneradorUI.nuevoPanelFiltro("Categorias", Backend.cargarCategorias(),true));
            pnlFiltros.add(GeneradorUI.nuevoPanelFiltro("Rubros", Backend.cargarRubros(),true));
            panel.add(pnlFiltros);
        }

        return panel;
    }

    private JScrollPane panelTabla(TipoRegistro tipo){
        String[] columnas = {};
        String[][] matriz = {};
        switch (tipo){
            case ITEM : {
                columnas = new String[]{"ID", "Nombre", "Precio_vta", "Marca", "Categoria", "Rubro"};
                Producto[] productos = Backend.cargarProductos();
                matriz = new String[productos.length][columnas.length];
                for (int i = 0; i < productos.length; i++) {
                    matriz[i][0] = productos[i].getId();
                    matriz[i][1] = productos[i].getNombre();
                    matriz[i][2] = "" + productos[i].getPrecioVenta();
                }
            }
            case CATEGORIA: {
                columnas = new String[]{"Nombre", "Rubro"};
                matriz = new String[5][2];
                for (int i = 0; i < matriz.length; i++) {
                    matriz[i][0] = "cat" + i;
                    matriz[i][1] = "rub0";
                }
            }
            case RUBRO: {
                columnas = new String[]{"Nombre"};
                matriz = new String[5][1];
                for (int i = 0; i < matriz.length; i++) {
                    matriz[i][0] = "rub" + i;
                }
            }
            case MARCA: {
                columnas = new String[]{"Nombre"};
                matriz = new String[10][1];
                for (int i = 0; i < matriz.length; i++) {
                    matriz[i][0] = "marca" + i;
                }
            }
        }

        JTable tabla = GeneradorUI.nuevaTabla(columnas,matriz,true, tipo);
        this.sorter = (TableRowSorter<DefaultTableModel>) tabla.getRowSorter();

        return new JScrollPane(tabla);
    }

    private void filtrar() {
        String texto = campoBusqueda.getText();
        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            // RowFilter.regexFilter busca en columnas indicadas (insensible a mayúsculas)
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 1, 2, 3));
        }
    }

}
