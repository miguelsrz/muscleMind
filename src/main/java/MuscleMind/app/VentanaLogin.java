package musclemind.app;

import musclemind.usuario.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaLogin extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoPIN;
    private JLabel mensajeError;
    private JCheckBox mostrarPIN;
    private ArrayList<Usuario> usuarios;

    public VentanaLogin(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;

        setTitle("MuscleMind - Iniciar Sesión");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Panel general
        JPanel fondo = new JPanel(new BorderLayout());

        // --- Panel superior ---
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(30, 30, 30));
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));

        JLabel titulo = new JLabel("MuscleMind");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 36));
        titulo.setForeground(Color.WHITE);
        panelSuperior.add(titulo);

        JLabel subtitulo = new JLabel("Bienvenido al sistema de rutinas personalizadas");
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 15));
        subtitulo.setForeground(Color.LIGHT_GRAY);
        panelSuperior.add(Box.createVerticalStrut(10));
        panelSuperior.add(subtitulo);

        fondo.add(panelSuperior, BorderLayout.NORTH);

        // --- Panel central ---
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(new Color(45, 45, 45));
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        Dimension tamanoEntrada = new Dimension(Integer.MAX_VALUE, 40);

        // Etiqueta usuario
        JLabel etiquetaUsuario = new JLabel("Nombre de usuario");
        etiquetaUsuario.setForeground(Color.WHITE);
        etiquetaUsuario.setFont(new Font("SansSerif", Font.BOLD, 14));
        etiquetaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(etiquetaUsuario);
        panelCentral.add(Box.createVerticalStrut(6));

        // Campo usuario
        campoUsuario = new JTextField();
        campoUsuario.setMaximumSize(tamanoEntrada);
        campoUsuario.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campoUsuario.setHorizontalAlignment(JTextField.CENTER);
        panelCentral.add(campoUsuario);

        JLabel ayudaUsuario = new JLabel("Ingresa tu nombre de usuario registrado.");
        ayudaUsuario.setFont(new Font("SansSerif", Font.ITALIC, 11));
        ayudaUsuario.setForeground(Color.GRAY);
        ayudaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        ayudaUsuario.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));
        panelCentral.add(ayudaUsuario);

        // Etiqueta PIN
        JLabel etiquetaPIN = new JLabel("PIN de acceso");
        etiquetaPIN.setForeground(Color.WHITE);
        etiquetaPIN.setFont(new Font("SansSerif", Font.BOLD, 14));
        etiquetaPIN.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(etiquetaPIN);
        panelCentral.add(Box.createVerticalStrut(6));

        // Campo PIN
        campoPIN = new JPasswordField();
        campoPIN.setMaximumSize(tamanoEntrada);
        campoPIN.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campoPIN.setEchoChar('•');
        campoPIN.setHorizontalAlignment(JTextField.CENTER);
        panelCentral.add(campoPIN);

        JLabel ayudaPIN = new JLabel("El PIN debe ser numérico (4 cifras).");
        ayudaPIN.setFont(new Font("SansSerif", Font.ITALIC, 11));
        ayudaPIN.setForeground(Color.GRAY);
        ayudaPIN.setAlignmentX(Component.CENTER_ALIGNMENT);
        ayudaPIN.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        panelCentral.add(ayudaPIN);

        // Checkbox
        mostrarPIN = new JCheckBox("Mostrar PIN");
        mostrarPIN.setBackground(new Color(45, 45, 45));
        mostrarPIN.setForeground(Color.LIGHT_GRAY);
        mostrarPIN.setFont(new Font("SansSerif", Font.PLAIN, 12));
        mostrarPIN.setAlignmentX(Component.CENTER_ALIGNMENT);
        mostrarPIN.addActionListener(e -> {
            campoPIN.setEchoChar(mostrarPIN.isSelected() ? (char) 0 : '•');
        });
        panelCentral.add(mostrarPIN);

        panelCentral.add(Box.createVerticalStrut(25));

        // Botón login (con mismo ancho)
        JButton botonLogin = new JButton("Iniciar Sesión");
        botonLogin.setFont(new Font("SansSerif", Font.BOLD, 16));
        botonLogin.setBackground(new Color(70, 130, 180));
        botonLogin.setForeground(Color.WHITE);
        botonLogin.setFocusPainted(false);
        botonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonLogin.setMaximumSize(tamanoEntrada);
        panelCentral.add(botonLogin);

        panelCentral.add(Box.createVerticalStrut(15));

        // Boton volver a inicio
        JButton botonVolver = new JButton("Volver al inicio");
        botonVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
        botonVolver.setBackground(Color.GRAY);
        botonVolver.setForeground(Color.WHITE);
        botonVolver.setFocusPainted(false);
        botonVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonVolver.setMaximumSize(tamanoEntrada);
        botonVolver.addActionListener(e -> {
            dispose();
            new VentanaPrincipal(usuarios).setVisible(true);
        });
        panelCentral.add(botonVolver);
        panelCentral.add(Box.createVerticalStrut(15));

        // Mensaje de error
        mensajeError = new JLabel("", SwingConstants.CENTER);
        mensajeError.setForeground(Color.RED);
        mensajeError.setFont(new Font("SansSerif", Font.PLAIN, 12));
        mensajeError.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(mensajeError);

        fondo.add(panelCentral, BorderLayout.CENTER);

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

        // Acción del botón
        botonLogin.addActionListener(e -> {
            String nombre = campoUsuario.getText().trim();
            char[] pinChars = campoPIN.getPassword();
            String pinStr = new String(pinChars);

            int pin;
            try {
                pin = Integer.parseInt(pinStr);
            } catch (NumberFormatException ex) {
                mensajeError.setText("El PIN debe ser numérico.");
                campoPIN.setText("");
                return;
            }

            Usuario usuarioActual = null;
            for (Usuario u : usuarios) {
                if (u.getNombreUsuario().equals(nombre) && u.getPin() == pin) {
                    usuarioActual = u;
                    break;
                }
            }

            if (usuarioActual != null) {
                dispose();
                new VentanaMenuUsuario(usuarioActual, usuarios).setVisible(true);
            } else {
                mensajeError.setText("Nombre o PIN incorrecto.");
                campoPIN.setText("");
            }
        });
    }
}
