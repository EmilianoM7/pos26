package emi;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import emi.front.MainMenu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Aplicar FlatLaf ANTES de crear cualquier componente
        //FlatLightLaf.setup(); // tema claro
        FlatDarkLaf.setup(); // ← alternativa oscura

        SwingUtilities.invokeLater(() -> {
            JFrame mainMenu = new MainMenu();
        });
    }
}