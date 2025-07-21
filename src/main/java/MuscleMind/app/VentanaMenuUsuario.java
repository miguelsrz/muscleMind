package musclemind.app;

import musclemind.usuario.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaMenuUsuario extends JFrame {

    private Usuario usuario;
    private ArrayList<Usuario> usuarios;

    public VentanaMenuUsuario(Usuario usuario, ArrayList<Usuario> usuarios) {
        this.usuario = usuario;
        this.usuarios = usuarios;

        setTitle("MuscleMind - Menú de Usuario");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Panel principal
        JPanel fondo = new JPanel(new BorderLayout());

        // Encabezado
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(30, 30, 30));
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(30, 20, 10, 20));

        JLabel titulo = new JLabel("MuscleMind - Menú Principal");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSuperior.add(titulo);

        JLabel bienvenida = new JLabel("¡Bienvenido, " + usuario.getNombreUsuario() + "!");
        bienvenida.setFont(new Font("SansSerif", Font.PLAIN, 16));
        bienvenida.setForeground(Color.LIGHT_GRAY);
        bienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSuperior.add(Box.createVerticalStrut(10));
        panelSuperior.add(bienvenida);

        fondo.add(panelSuperior, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(new Color(45, 45, 45));
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        Font fontBoton = new Font("SansSerif", Font.BOLD, 16);
        Font fontDesc = new Font("SansSerif", Font.PLAIN, 12);
        Dimension tamanoBoton = new Dimension(360, 40);

        // Botones principales
        addBotonConDescripcion(panelCentral, "Ver Rutinas", "Consulta las rutinas de ejercicio ya guardadas", fontBoton, fontDesc, tamanoBoton, new Color(70, 130, 180));
        addBotonConDescripcion(panelCentral, "Crear Rutina Nueva", "Accede al catálogo para diseñar tu propia rutina", fontBoton, fontDesc, tamanoBoton, new Color(70, 130, 180));
        addBotonConDescripcion(panelCentral, "Registrar Sesión", "Ejecuta una rutina y registra tu progreso diario", fontBoton, fontDesc, tamanoBoton, new Color(70, 130, 180));
        addBotonConDescripcion(panelCentral, "Ver Histórico", "Revisa el historial de tus sesiones anteriores", fontBoton, fontDesc, tamanoBoton, new Color(70, 130, 180));

        panelCentral.add(Box.createVerticalStrut(40)); // separación antes de cerrar sesión

        // Botón cerrar sesión
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(fontBoton);
        btnCerrarSesion.setPreferredSize(tamanoBoton);
        btnCerrarSesion.setMaximumSize(tamanoBoton);
        btnCerrarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCerrarSesion.setBackground(new Color(60, 63, 65));
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFocusPainted(false);
        panelCentral.add(btnCerrarSesion);

        JLabel descCerrar = new JLabel("Finaliza tu sesión y vuelve al inicio de la app");
        descCerrar.setFont(fontDesc);
        descCerrar.setForeground(Color.LIGHT_GRAY);
        descCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        descCerrar.setBorder(BorderFactory.createEmptyBorder(8, 0, 15, 0));
        panelCentral.add(descCerrar);

        fondo.add(panelCentral, BorderLayout.CENTER);

        // Pie de página
        JPanel pie = new JPanel();
        pie.setBackground(new Color(30, 30, 30));
        pie.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel textoPie = new JLabel("Desarrollado por: Josue Caviativa, Cristian Parada, Miguel Suarez");
        textoPie.setForeground(Color.GRAY);
        textoPie.setFont(new Font("SansSerif", Font.PLAIN, 11));
        pie.add(textoPie);
        fondo.add(pie, BorderLayout.SOUTH);

        add(fondo);

        // Acción cerrar sesión
        btnCerrarSesion.addActionListener(e -> {
            dispose();
            new VentanaPrincipal(usuarios).setVisible(true);
        });
    }

    private void addBotonConDescripcion(JPanel panel, String textoBoton, String descripcion,
            Font fontBoton, Font fontDesc, Dimension tamano, Color colorFondo) {

        JButton boton = new JButton(textoBoton);
        boton.setFont(fontBoton);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setPreferredSize(tamano);
        boton.setMaximumSize(tamano);
        panel.add(boton);

        JLabel etiqueta = new JLabel(descripcion);
        etiqueta.setFont(fontDesc);
        etiqueta.setForeground(Color.LIGHT_GRAY);
        etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiqueta.setBorder(BorderFactory.createEmptyBorder(10, 0, 25, 0));
        panel.add(etiqueta);

        // Agrega acción según el texto del botón
        boton.addActionListener(e -> {
            switch (textoBoton) {
                case "Crear Rutina Nueva":
                    dispose();
                    new PanelCrearRutina(usuario, usuarios).setVisible(true);
                    break;
                case "Ver Rutinas":
                    dispose();
                    new VentanaVerRutinas(usuario, usuarios).setVisible(true);
                    break;
                case "Registrar Sesión":
                    JOptionPane.showMessageDialog(panel, "Funcionalidad Registrar Sesión aún no implementada.");
                    break;
                case "Ver Histórico":
                    JOptionPane.showMessageDialog(panel, "Funcionalidad Ver Histórico aún no implementada.");
                    break;
            }
        });
    }

}
