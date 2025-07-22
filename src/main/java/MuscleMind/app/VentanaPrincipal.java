package musclemind.app;

import musclemind.usuario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {

    private ArrayList<Usuario> usuarios;

    public VentanaPrincipal(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;

        setTitle("MuscleMind");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(new Color(30, 30, 30));

        // Parte superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(30, 30, 30));
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));

        JLabel titulo = new JLabel("MuscleMind");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 36));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Tu asistente de entrenamiento inteligente");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 15));
        subtitulo.setForeground(Color.LIGHT_GRAY);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelSuperior.add(titulo);
        panelSuperior.add(Box.createVerticalStrut(10));
        panelSuperior.add(subtitulo);

        fondo.add(panelSuperior, BorderLayout.NORTH);

        // Parte central con los botones que llevan a las partes indicadas
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(new Color(45, 45, 45));
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(80, 100, 80, 100));

        Dimension botonSize = new Dimension(Integer.MAX_VALUE, 40);

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setMaximumSize(botonSize);
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setBackground(new Color(70, 130, 180));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(e -> {
            dispose();
            new VentanaLogin(usuarios).setVisible(true);
        });

        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.setMaximumSize(botonSize);
        btnRegistro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistro.setBackground(Color.GRAY);
        btnRegistro.setForeground(Color.WHITE);
        btnRegistro.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnRegistro.setFocusPainted(false);
        btnRegistro.addActionListener(e -> {
            dispose();
            new PanelRegistro(usuarios).setVisible(true);
        });

        JButton btnSalir = new JButton("Salir");
        btnSalir.setMaximumSize(botonSize);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setBackground(Color.DARK_GRAY);
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnSalir.setFocusPainted(false);
        btnSalir.addActionListener(e -> System.exit(0));

        panelCentral.add(btnLogin);
        panelCentral.add(Box.createVerticalStrut(20));
        panelCentral.add(btnRegistro);
        panelCentral.add(Box.createVerticalStrut(20));
        panelCentral.add(btnSalir);

        fondo.add(panelCentral, BorderLayout.CENTER);

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
}
