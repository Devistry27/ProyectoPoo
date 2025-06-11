package Main;

import javax.swing.SwingUtilities;
import View.VistaPrincipal;
import Controller.ControladorBiblioteca;
import model.AuthService;
import model.Biblioteca;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaPrincipal vista = new VistaPrincipal();
            vista.setVisible(true);
            new ControladorBiblioteca(vista);
        });
    }
}
