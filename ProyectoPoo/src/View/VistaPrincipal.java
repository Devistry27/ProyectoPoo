package View;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends JFrame {
    private JTextField campoNombreUsuario, campoCorreoUsuario, campoClaveUsuario, campoIdMulta;
    private JComboBox<String> comboBoxRol;
    private JTextField campoCarreraDepartamento;
    private JButton btnRegistrarUsuario, btnIniciarSesion;

    private JTextField campoTituloLibro, campoAutorLibro, campoISBN, campoCategoria, campoUbicacion;
    private JComboBox<String> comboFormato;
    private JButton btnAgregarLibro, btnPrestarLibro, btnDevolverLibro, btnActualizarUsuario, btnCalcularMulta, btnPagarMulta;
    private JButton btnRenovarPrestamo, btnVerHistorial, btnVerMultas;

    private JTextField campoDiasRetraso;
    private JTextArea areaMensajes;

    public VistaPrincipal() {
        setTitle("Sistema de Biblioteca");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        // Paneles agrupados para secciones visualmente organizadas
        JPanel panelUsuario = new JPanel(new GridLayout(0, 2));
        panelUsuario.setBorder(BorderFactory.createTitledBorder("Gestión de Usuario"));
        panelUsuario.add(new JLabel("Nombre de Usuario:")); campoNombreUsuario = new JTextField(); panelUsuario.add(campoNombreUsuario);
        panelUsuario.add(new JLabel("Correo:")); campoCorreoUsuario = new JTextField(); panelUsuario.add(campoCorreoUsuario);
        panelUsuario.add(new JLabel("Contraseña:")); campoClaveUsuario = new JTextField(); panelUsuario.add(campoClaveUsuario);
        panelUsuario.add(new JLabel("Rol:")); comboBoxRol = new JComboBox<>(new String[]{"Estudiante", "Profesor", "Bibliotecario"}); panelUsuario.add(comboBoxRol);
        panelUsuario.add(new JLabel("Carrera / Departamento:")); campoCarreraDepartamento = new JTextField(); panelUsuario.add(campoCarreraDepartamento);
        btnRegistrarUsuario = new JButton("Registrar Usuario"); panelUsuario.add(btnRegistrarUsuario);
        btnIniciarSesion = new JButton("Iniciar Sesión"); panelUsuario.add(btnIniciarSesion);

        JPanel panelLibro = new JPanel(new GridLayout(0, 2));
        panelLibro.setBorder(BorderFactory.createTitledBorder("Gestión de Libros"));
        panelLibro.add(new JLabel("Título:")); campoTituloLibro = new JTextField(); panelLibro.add(campoTituloLibro);
        panelLibro.add(new JLabel("Autor:")); campoAutorLibro = new JTextField(); panelLibro.add(campoAutorLibro);
        panelLibro.add(new JLabel("ISBN:")); campoISBN = new JTextField(); panelLibro.add(campoISBN);
        panelLibro.add(new JLabel("Categoría:")); campoCategoria = new JTextField(); panelLibro.add(campoCategoria);
        panelLibro.add(new JLabel("Ubicación Física:")); campoUbicacion = new JTextField(); panelLibro.add(campoUbicacion);
        panelLibro.add(new JLabel("Formato:")); comboFormato = new JComboBox<>(new String[]{"Físico", "eBook", "Audiolibro"}); panelLibro.add(comboFormato);
        btnAgregarLibro = new JButton("Agregar Libro"); panelLibro.add(btnAgregarLibro);
        btnPrestarLibro = new JButton("Prestar Libro"); panelLibro.add(btnPrestarLibro);
        btnDevolverLibro = new JButton("Devolver Libro"); panelLibro.add(btnDevolverLibro);

        JPanel panelMultas = new JPanel(new GridLayout(0, 2));
        panelMultas.setBorder(BorderFactory.createTitledBorder("Multas y Renovaciones"));
        btnActualizarUsuario = new JButton("Actualizar Usuario"); panelMultas.add(btnActualizarUsuario);
        panelMultas.add(new JLabel("Días de Retraso:")); campoDiasRetraso = new JTextField(); panelMultas.add(campoDiasRetraso);
        btnCalcularMulta = new JButton("Calcular Multa"); panelMultas.add(btnCalcularMulta);
        btnRenovarPrestamo = new JButton("Renovar Préstamo"); panelMultas.add(btnRenovarPrestamo);
        btnVerHistorial = new JButton("Ver Historial" ); panelMultas.add(btnVerHistorial);
        btnVerMultas = new JButton("Ver Multas" ); panelMultas.add(btnVerMultas);
        panelMultas.add(new JLabel("ID Multa a pagar:")); campoIdMulta = new JTextField(); panelMultas.add(campoIdMulta);
        btnPagarMulta = new JButton("Pagar Multa"); panelMultas.add(btnPagarMulta);

        // Área de mensajes en panel fijo
        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaMensajes);

        JPanel panelMensajes = new JPanel(new BorderLayout());
        panelMensajes.setBorder(BorderFactory.createTitledBorder("Mensajes del sistema"));
        panelMensajes.setPreferredSize(new Dimension(800, 150));
        panelMensajes.add(scroll, BorderLayout.CENTER);

        // Agregamos los paneles organizados
        panelPrincipal.add(panelUsuario);
        panelPrincipal.add(panelLibro);
        panelPrincipal.add(panelMultas);

        add(panelPrincipal, BorderLayout.CENTER);
        add(panelMensajes, BorderLayout.SOUTH);
    }

    // --- Getters ---
    public String getNombreUsuario() { return campoNombreUsuario.getText(); }
    public String getCorreoUsuario() { return campoCorreoUsuario.getText(); }
    public String getClaveUsuario() { return campoClaveUsuario.getText(); }
    public String getRolUsuario() { return comboBoxRol.getSelectedItem().toString(); }
    public String getCarreraDepartamento() { return campoCarreraDepartamento.getText(); }
    public String getTituloLibro() { return campoTituloLibro.getText(); }
    public String getAutorLibro() { return campoAutorLibro.getText(); }
    public String getISBNLibro() { return campoISBN.getText(); }
    public String getCategoriaLibro() { return campoCategoria.getText(); }
    public String getUbicacionFisicaLibro() { return campoUbicacion.getText(); }
    public String getFormatoLibro() { return comboFormato.getSelectedItem().toString(); }
    public int getDiasRetraso() {
        try {
            return Integer.parseInt(campoDiasRetraso.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getIdMultaSeleccionada() {
        return campoIdMulta.getText().trim();
    }
    


    public JButton getBtnRegistrarUsuario() { return btnRegistrarUsuario; }
    public JButton getBtnIniciarSesion() { return btnIniciarSesion; }
    public JButton getBtnAgregarLibro() { return btnAgregarLibro; }
    public JButton getBtnPrestarLibro() { return btnPrestarLibro; }
    public JButton getBtnDevolverLibro() { return btnDevolverLibro; }
    public JButton getBtnActualizarUsuario() { return btnActualizarUsuario; }
    public JButton getBtnCalcularMulta() { return btnCalcularMulta; }
    public JButton getBtnRenovarPrestamo() { return btnRenovarPrestamo; }
    public JButton getBtnVerHistorial() { return btnVerHistorial; }
    public JButton getBtnVerMultas() { return btnVerMultas; }
    public JButton getBtnPagarMulta() { return btnPagarMulta; }

    public void mostrarMensaje(String mensaje) {
        areaMensajes.append(mensaje + "\n");
    }

    public void actualizarListaLibros(String lista) {
        areaMensajes.append("\nCatálogo de Libros:\n" + lista + "\n");
    }
}
