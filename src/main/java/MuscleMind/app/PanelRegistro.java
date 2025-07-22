package musclemind.app;

import musclemind.usuario.Usuario;
import musclemind.rutina.Rutina;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class PanelRegistro extends JFrame {

    private JTextField campoNombre, campoEdad, campoPeso;
    private JPasswordField campoPIN;
    private JComboBox<String> comboObjetivo;
    private JLabel mensajeError;
    private ArrayList<Usuario> usuarios;

    public PanelRegistro(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;

        setTitle("MuscleMind - Registro");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(new Color(30, 30, 30));

        // Panel Superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setBackground(new Color(30, 30, 30));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));

        JLabel titulo = new JLabel("Registro - MuscleMind");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 30));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Crea tu cuenta para comenzar tu progreso");
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitulo.setForeground(Color.LIGHT_GRAY);

        panelSuperior.add(titulo);
        panelSuperior.add(Box.createVerticalStrut(10));
        panelSuperior.add(subtitulo);

        fondo.add(panelSuperior, BorderLayout.NORTH);

        // Panel central, con el formulario para completar el registro
        JPanel central = new JPanel();
        central.setBackground(new Color(45, 45, 45));
        central.setLayout(new BoxLayout(central, BoxLayout.Y_AXIS));
        central.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        campoNombre = crearCampoTexto("Nombre de usuario", "Este será tu nombre de acceso.", central);
        campoPIN = crearCampoPassword("PIN (4 dígitos)", "Debe contener exactamente 4 números.", central);
        campoEdad = crearCampoTexto("Edad", "Tu edad actual (en años).", central);
        campoPeso = crearCampoTexto("Peso (kg)", "Tu peso corporal aproximado.", central);

        // Se selecciona el objetivo, elimina riesgo de error al no cumplir condicionales de objetivo en clase usuario
        JLabel lblObjetivo = new JLabel("Objetivo");
        lblObjetivo.setForeground(Color.WHITE);
        lblObjetivo.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblObjetivo.setAlignmentX(Component.CENTER_ALIGNMENT);

        comboObjetivo = new JComboBox<>(new String[]{
            "Hipertrofia", "Definicion", "Resistencia", "Recuperacion"
        });
        comboObjetivo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        comboObjetivo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        comboObjetivo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel ayudaObjetivo = new JLabel("Selecciona tu enfoque principal.");
        ayudaObjetivo.setFont(new Font("SansSerif", Font.ITALIC, 11));
        ayudaObjetivo.setForeground(Color.GRAY);
        ayudaObjetivo.setAlignmentX(Component.CENTER_ALIGNMENT);
        ayudaObjetivo.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));

        central.add(lblObjetivo);
        central.add(Box.createVerticalStrut(6));
        central.add(comboObjetivo);
        central.add(ayudaObjetivo);

        central.add(Box.createVerticalStrut(10));

        // Manejo de error basico
        mensajeError = new JLabel("", SwingConstants.CENTER);
        mensajeError.setForeground(Color.RED);
        mensajeError.setFont(new Font("SansSerif", Font.PLAIN, 12));
        mensajeError.setAlignmentX(Component.CENTER_ALIGNMENT);
        central.add(mensajeError);

        central.add(Box.createVerticalStrut(20));

        // Botones
        Dimension tamanoBoton = new Dimension(Integer.MAX_VALUE, 50);

        JButton btnVolver = new JButton("Volver");
        estilizarBoton(btnVolver, Color.GRAY);
        btnVolver.setMaximumSize(tamanoBoton);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.addActionListener(e -> {
            dispose();
            new VentanaPrincipal(usuarios).setVisible(true);
        });

        JButton btnRegistrar = new JButton("Registrarse");
        estilizarBoton(btnRegistrar, new Color(70, 130, 180));
        btnRegistrar.setMaximumSize(tamanoBoton);
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrar.addActionListener(e -> registrar());

        central.add(btnRegistrar);
        central.add(Box.createVerticalStrut(15));
        central.add(btnVolver);

        JScrollPane scroll = new JScrollPane(central);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        fondo.add(scroll, BorderLayout.CENTER);

        // --- Pie de página ---
        JPanel pie = new JPanel();
        pie.setBackground(new Color(30, 30, 30));
        pie.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        JLabel textoPie = new JLabel("Desarrollado por: Josue Caviativa, Cristian Parada, Miguel Suarez");
        textoPie.setForeground(Color.GRAY);
        textoPie.setFont(new Font("SansSerif", Font.PLAIN, 11));
        textoPie.setHorizontalAlignment(SwingConstants.CENTER);
        pie.setLayout(new BorderLayout());
        pie.add(textoPie, BorderLayout.CENTER);

        fondo.add(pie, BorderLayout.SOUTH);
        add(fondo);
    }

    // Reduccion de codigo para la creacion de interfaz
    private JTextField crearCampoTexto(String etiqueta, String ayuda, JPanel panel) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campo = new JTextField();
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campo.setHorizontalAlignment(JTextField.CENTER);

        JLabel lblAyuda = new JLabel(ayuda);
        lblAyuda.setFont(new Font("SansSerif", Font.ITALIC, 11));
        lblAyuda.setForeground(Color.GRAY);
        lblAyuda.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblAyuda.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));

        panel.add(lbl);
        panel.add(Box.createVerticalStrut(6));
        panel.add(campo);
        panel.add(lblAyuda);

        return campo;
    }

    // Reduccion de codigo para la creacion de interfaz
    private JPasswordField crearCampoPassword(String etiqueta, String ayuda, JPanel panel) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField campo = new JPasswordField();
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        campo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campo.setHorizontalAlignment(JTextField.CENTER);
        campo.setEchoChar('•');

        JLabel lblAyuda = new JLabel(ayuda);
        lblAyuda.setFont(new Font("SansSerif", Font.ITALIC, 11));
        lblAyuda.setForeground(Color.GRAY);
        lblAyuda.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblAyuda.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));

        panel.add(lbl);
        panel.add(Box.createVerticalStrut(6));
        panel.add(campo);
        panel.add(lblAyuda);

        return campo;
    }

    // Reduccion de codigo para estilizar
    private void estilizarBoton(JButton boton, Color colorFondo) {
        boton.setFont(new Font("SansSerif", Font.BOLD, 16));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
    }

    // Realiza las validaciones y crea el nuevo usuario con la informacion suministrada
    private void registrar() {
        try {
            String nombre = campoNombre.getText().trim();
            String pinStr = new String(campoPIN.getPassword()).trim();
            String edadStr = campoEdad.getText().trim();
            String pesoStr = campoPeso.getText().trim();
            String objetivo = Objects.toString(comboObjetivo.getSelectedItem(), "hipertrofia").toLowerCase();

            if (!pinStr.matches("\\d{4}")) {
                mostrarError("El PIN debe tener exactamente 4 dígitos.");
                return;
            }

            Usuario nuevo = new Usuario(
                    nombre,
                    Integer.parseInt(pinStr),
                    Integer.parseInt(edadStr),
                    Integer.parseInt(pesoStr),
                    objetivo,
                    Rutina.generarRutinaPorObjetivo(objetivo)
            );

            boolean existe = usuarios.stream().anyMatch(u -> u.getNombreUsuario().equalsIgnoreCase(nombre));
            if (existe) {
                mostrarError("El nombre de usuario ya existe.");
                return;
            }

            usuarios.add(nuevo);
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
            dispose();
            new VentanaPrincipal(usuarios).setVisible(true);
        } catch (NumberFormatException ex) {
            mostrarError("Edad, peso y PIN deben ser numéricos.");
        }
    }

    // Manejo de error basico
    private void mostrarError(String mensaje) {
        mensajeError.setText(mensaje);
    }
}
