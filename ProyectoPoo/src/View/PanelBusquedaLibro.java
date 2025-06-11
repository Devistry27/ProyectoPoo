package View;

import javax.swing.*;

public class PanelBusquedaLibro extends JPanel {
    private JTextField campoTitulo;
    private JButton btnBuscar;

    public PanelBusquedaLibro() {
        campoTitulo = new JTextField(15);
        btnBuscar = new JButton("Buscar");

        add(new JLabel("Titulo:"));
        add(campoTitulo);
        add(btnBuscar);
    }

    public String getTitulo() {
        return campoTitulo.getText();
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }
}

