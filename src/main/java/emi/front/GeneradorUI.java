package emi.front;

import com.formdev.flatlaf.FlatClientProperties;
import emi.back.Backend;
import emi.domain.TipoRegistro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class GeneradorUI {

    private static float[] font_label = {20f,16f,12f};

    private static JPanel panel(int paddingH, int paddingV){
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(paddingV, paddingH, paddingV, paddingH));
        return panel;
    }

    public static JPanel nuevoPanelFiltro(String titulo, String[] lista, boolean vertical){
        JPanel pnlFiltro = vertical ? nuevoPanelVertical(0,0) : nuevoPanelHorizontal(false,0,0);
        pnlFiltro.add(nuevoLabel(titulo + ":",2));
        //pnlFiltro.setBorder(BorderFactory.createTitledBorder(titulo));
        pnlFiltro.add(nuevoComboBox(lista));
        return pnlFiltro;
    }

    public static JPanel nuevoPanelRadio(String titulo, String[] lista, boolean vertical){
        JPanel pnlRadio = vertical ? nuevoPanelVertical(0,0) : nuevoPanelHorizontal(false,0,0);
        //pnlRadio.add(nuevoLabel(titulo + ":",2));
        pnlRadio.setBorder(BorderFactory.createTitledBorder(titulo));
        ButtonGroup grupo = new ButtonGroup();
        for (int i = 0; i < lista.length; i++) {
            grupo.add(new JRadioButton(lista[i]));
        }
        return pnlRadio;
    }

    public static JPanel nuevoPanelBorder(int paddingH, int paddingV){
        JPanel panel = panel(paddingH,paddingV);
        panel.setLayout(new BorderLayout(0, 10));
        return panel;
    }

    public static JPanel nuevoPanelHorizontal(boolean derecha, int paddingH, int paddingV){
        JPanel panel = panel(paddingH,paddingV);
        panel.setLayout(new FlowLayout(derecha ? FlowLayout.RIGHT : FlowLayout.LEFT, 10, 0));
        return panel;
    }

    public static JPanel nuevoPanelVertical(int paddingH, int paddingV){
        JPanel panel = panel(paddingH,paddingV);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    public static JPanel nuevoPanelGrid(int paddingH, int paddingV){
        JPanel panel = panel(paddingH,paddingV);
        panel.setLayout(new GridLayout(0, 1, 5, 5));
        return panel;
    }

    public static JComponent nuevoSeparador(boolean vertical, int ancho){
        JSeparator separador = new JSeparator();
        separador.setPreferredSize(new Dimension(separador.getPreferredSize().width, ancho));
        separador.setOrientation(vertical ? SwingConstants.VERTICAL : SwingConstants.HORIZONTAL);
        //separador.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        return separador;
    }

    public static JLabel nuevoLabel(String texto, int tamanoTexto ){
        JLabel nuevo = new JLabel(texto);
        nuevo.setFont(nuevo.getFont().deriveFont(Font.BOLD, font_label[tamanoTexto]));
        //
        //nuevo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return nuevo;
    }

    public static JPanel nuevoLabelHorizontal(String texto, int tamanoTexto ){
        JPanel pnl = GeneradorUI.nuevoPanelHorizontal(false,0,0);
        pnl.add(nuevoLabel(texto,tamanoTexto));
        return pnl;
    }

    public static JButton nuevoButton (String texto, Icon icon, ActionListener accion){
        JButton boton = new JButton(texto,icon);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);

        boton.addActionListener(accion != null ? accion :
                e -> System.out.println("BTN: " + texto));

        return boton;
    }

    public static JTextField nuevoTextField(String placeHolder, int ancho){
        JTextField campo = new JTextField();
        campo.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, placeHolder);
        campo.setPreferredSize(new Dimension( (ancho != 0 ? ancho : 300), 32));
        return campo;
    }

    public static JComboBox<String> nuevoComboBox(String[] opciones){
        return new JComboBox<>(opciones);
    }

    public static JTable nuevaTabla(String[] columnas, Object[][] matriz, boolean editable, TipoRegistro tipo){
        DefaultTableModel modelo;
        JTable tabla = new JTable();
        if (editable){
            modelo = new DefaultTableModel(columnas,0){
                @Override
                public void setValueAt(Object valor, int row, int col){
                    Object valorAnterior = getValueAt(row, col);

                    // Si el valor no cambió, ignorar
                    if (valor == null || valor.equals(valorAnterior)) return;

                    // preparar comparacion edicion
                    Object[][] matrizComparacion = new Object[2][columnas.length];
                    for (int i = 0; i < columnas.length; i++) {
                        matrizComparacion[0][i] = getValueAt(row,i);
                        matrizComparacion[1][i] = (i == col) ? valor : getValueAt(row,i);
                    }
                    JTable comparacion = nuevaTabla(columnas, matrizComparacion, false, null);
                    JScrollPane scroll =  new JScrollPane(comparacion);
                    scroll.setPreferredSize(new Dimension(500, comparacion.getRowHeight() * 4));
                    // pedir confirmacion
                    if (
                        JOptionPane.showConfirmDialog(null,
                                scroll,
                                "¿Confirmar cambios?",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        ) == JOptionPane.YES_NO_OPTION
                    ){
                        Backend.editarRegistro(tipo,matrizComparacion[1]);
                        super.setValueAt(valor, row, col);
                    }

                }
            };
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
            tabla.setRowSorter(sorter);
        }
        else {
            modelo = new DefaultTableModel(columnas,0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }
        tabla.setModel(modelo);

        if (matriz != null){
            for (Object[] linea : matriz){
                modelo.addRow(linea);
            }
        }
        return tabla;
    }

    public static void setUpFrame(JFrame ventana, JFrame mainOcultar){
        ventana.setTitle("POS_26s");
        ventana.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        mainOcultar.setVisible(false);
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(ventana, "¿Volver al Menu Principal?");
                if (confirm == JOptionPane.YES_OPTION) {
                    mainOcultar.setVisible(true);
                    ventana.dispose();
                }
            }
        });
    }
}
