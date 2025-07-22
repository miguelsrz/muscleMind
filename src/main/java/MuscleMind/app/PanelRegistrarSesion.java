package musclemind.app;

import musclemind.usuario.*;
import musclemind.rutina.*;
import musclemind.ejercicio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class PanelRegistrarSesion extends JFrame {

    private Usuario usuario;
    private ArrayList<Usuario> usuarios;
    private JComboBox<Rutina> comboRutinas;
    private JPanel panelEjercicios;
    private JTextField campoDuracionTotal;
    private ArrayList<ArrayList<JTextField>> camposPorEjercicio;

    public PanelRegistrarSesion(Usuario usuario, ArrayList<Usuario> usuarios) {
        this.usuario = usuario;
        this.usuarios = usuarios;

        setTitle("MuscleMind - Registrar Sesión");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        JLabel titulo = new JLabel("Registrar sesión");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        // Parte superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setBackground(new Color(30, 30, 30));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

    
        JLabel tituloSesion = new JLabel("Registrar Nueva Sesión");
        tituloSesion.setForeground(Color.WHITE);
        tituloSesion.setFont(new Font("SansSerif", Font.BOLD, 24));
        tituloSesion.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelSuperior.add(tituloSesion);
        panelSuperior.add(Box.createVerticalStrut(10));

        JLabel labelRutina = new JLabel("Selecciona una rutina:");
        labelRutina.setForeground(Color.WHITE);
        labelRutina.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelRutina.setAlignmentX(Component.CENTER_ALIGNMENT); // centrado

        comboRutinas = new JComboBox<>(usuario.getListaRutinas().toArray(new Rutina[0]));
        comboRutinas.setFont(new Font("SansSerif", Font.PLAIN, 14));
        comboRutinas.setMaximumSize(new Dimension(400, 30));
        comboRutinas.setAlignmentX(Component.CENTER_ALIGNMENT); // centrado
        comboRutinas.addActionListener(e -> actualizarPanelEjercicios());

        panelSuperior.add(labelRutina);
        panelSuperior.add(Box.createVerticalStrut(5));
        panelSuperior.add(comboRutinas);
        add(panelSuperior, BorderLayout.BEFORE_FIRST_LINE);

        // Panel donde se muestran los ejercicios y los campos a completar
        JPanel contenedorCentro = new JPanel(new GridBagLayout());
        contenedorCentro.setBackground(new Color(30, 30, 30));

        panelEjercicios = new JPanel();
        panelEjercicios.setLayout(new BoxLayout(panelEjercicios, BoxLayout.Y_AXIS));
        panelEjercicios.setBackground(new Color(40, 40, 40));
        panelEjercicios.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelEjercicios.setMaximumSize(new Dimension(520, 580));

        JScrollPane scroll = new JScrollPane(panelEjercicios);
        scroll.setPreferredSize(new Dimension(540, 400));
        scroll.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        contenedorCentro.add(scroll);
        add(contenedorCentro, BorderLayout.CENTER);

        // Parte inferior
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
        panelInferior.setBackground(new Color(30, 30, 30));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));

        JPanel panelDuracion = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelDuracion.setBackground(new Color(30, 30, 30));

        JLabel labelDuracion = new JLabel("Duración total de la sesión (min):");
        labelDuracion.setForeground(Color.WHITE);
        labelDuracion.setFont(new Font("SansSerif", Font.BOLD, 14));

        campoDuracionTotal = new JTextField(10);
        campoDuracionTotal.setPreferredSize(new Dimension(100, 30));
        campoDuracionTotal.setFont(new Font("SansSerif", Font.PLAIN, 13));

        panelDuracion.add(labelDuracion);
        panelDuracion.add(campoDuracionTotal);

        // Botones parte inferior
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(new Color(30, 30, 30));

        JButton botonRegistrar = new JButton("Registrar sesión");
        botonRegistrar.setBackground(new Color(70, 130, 180));
        botonRegistrar.setForeground(Color.WHITE);
        botonRegistrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        botonRegistrar.setPreferredSize(new Dimension(160, 40));
        botonRegistrar.addActionListener(this::registrarSesion);

        JButton botonVolver = new JButton("Volver");
        botonVolver.setBackground(new Color(100, 100, 100));
        botonVolver.setForeground(Color.WHITE);
        botonVolver.setFont(new Font("SansSerif", Font.BOLD, 14));
        botonVolver.setPreferredSize(new Dimension(100, 40));
        botonVolver.addActionListener(e -> {
            dispose();
            new VentanaMenuUsuario(usuario, usuarios).setVisible(true);
        });

        panelBotones.add(botonRegistrar);
        panelBotones.add(Box.createHorizontalStrut(20));
        panelBotones.add(botonVolver);

        panelInferior.add(panelDuracion);
        panelInferior.add(Box.createVerticalStrut(10));
        panelInferior.add(panelBotones);
        add(panelInferior, BorderLayout.SOUTH);

        actualizarPanelEjercicios();
    }

    // Al cambiar de rutina se actualiza el panel de ejercicios con los ejercicios correspondientes
    private void actualizarPanelEjercicios() {
        panelEjercicios.removeAll();
        camposPorEjercicio = new ArrayList<>();

        Rutina rutina = (Rutina) comboRutinas.getSelectedItem();
        if (rutina != null) {
            // Contenedor alineado a la izquierda para el resumen
            JPanel resumenPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            resumenPanel.setBackground(new Color(40, 40, 40));  // Igual al fondo del panelEjercicios

            JLabel resumen = new JLabel("Rutina: " + rutina.getNombreRutina() + " | Total ejercicios: " + rutina.getListaEjercicios().size());
            resumen.setForeground(Color.WHITE);
            resumen.setFont(new Font("SansSerif", Font.PLAIN, 13));
            resumenPanel.add(resumen);

            panelEjercicios.add(resumenPanel);
            panelEjercicios.add(Box.createVerticalStrut(10));

            for (Ejercicio e : rutina.getListaEjercicios()) {
                JPanel fila = new JPanel();
                fila.setLayout(new BoxLayout(fila, BoxLayout.Y_AXIS));
                fila.setBackground(new Color(60, 60, 60));
                fila.setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.GRAY),
                        e.getNombreEjercicio(),
                        0, 0, new Font("SansSerif", Font.BOLD, 14), Color.WHITE
                ));

                ArrayList<JTextField> campos = new ArrayList<>();

                if (e instanceof EjerciciosFuerza fuerza) {
                    for (int i = 1; i <= fuerza.getCantidadSeries(); i++) {
                        JPanel filaSerie = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        filaSerie.setBackground(new Color(60, 60, 60));

                        JLabel l1 = new JLabel("Serie " + i + " - Peso (kg):");
                        l1.setForeground(Color.WHITE);
                        JTextField peso = new JTextField(5);
                        peso.setPreferredSize(new Dimension(70, 30));
                        peso.setFont(new Font("SansSerif", Font.PLAIN, 13));
                        campos.add(peso);

                        JLabel l2 = new JLabel("Repeticiones:");
                        l2.setForeground(Color.WHITE);
                        JTextField rep = new JTextField(5);
                        rep.setPreferredSize(new Dimension(70, 30));
                        rep.setFont(new Font("SansSerif", Font.PLAIN, 13));
                        campos.add(rep);

                        filaSerie.add(l1);
                        filaSerie.add(peso);
                        filaSerie.add(l2);
                        filaSerie.add(rep);
                        fila.add(filaSerie);
                    }

                    JPanel descansoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    descansoPanel.setBackground(new Color(60, 60, 60));
                    JLabel descansoLabel = new JLabel("Descanso promedio (s):");
                    descansoLabel.setForeground(Color.WHITE);
                    JTextField descanso = new JTextField(5);
                    descanso.setPreferredSize(new Dimension(70, 30));
                    descanso.setFont(new Font("SansSerif", Font.PLAIN, 13));
                    campos.add(descanso);
                    descansoPanel.add(descansoLabel);
                    descansoPanel.add(descanso);
                    fila.add(descansoPanel);

                } else if (e instanceof EjerciciosCardio) {
                    fila.add(crearCampo("Intensidad (km/h):", campos));
                    fila.add(crearCampo("Duración (min):", campos));
                    fila.add(crearCampo("Frec. cardiaca promedio:", campos));
                } 

                panelEjercicios.add(Box.createVerticalStrut(10));
                panelEjercicios.add(fila);
                camposPorEjercicio.add(campos);
            }
        }

        panelEjercicios.revalidate();
        panelEjercicios.repaint();
    }
    
    // Reduccion de codigo para crear interfaz
    private JPanel crearCampo(String label, ArrayList<JTextField> campos) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(60, 60, 60));
        JLabel etiqueta = new JLabel(label);
        etiqueta.setForeground(Color.WHITE);
        JTextField campo = new JTextField(10);
        campo.setPreferredSize(new Dimension(100, 30));
        campo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        campos.add(campo);
        panel.add(etiqueta);
        panel.add(campo);
        return panel;
    }

    // Realiza las validaciones y registra la sesion en la rutina y usuario especifico
    private void registrarSesion(ActionEvent evt) {
        Rutina rutina = (Rutina) comboRutinas.getSelectedItem();
        if (rutina == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una rutina válida.");
            return;
        }

        try {
            int duracionTotal = Integer.parseInt(campoDuracionTotal.getText().trim());
            ArrayList<ArrayList<String>> resultados = new ArrayList<>();

            for (ArrayList<JTextField> grupo : camposPorEjercicio) {
                ArrayList<String> resultadosEjercicio = new ArrayList<>();
                for (JTextField campo : grupo) {
                    resultadosEjercicio.add(campo.getText().trim());
                }
                resultados.add(resultadosEjercicio);
            }

            rutina.registrarSesion(duracionTotal, 0, resultados);
            JOptionPane.showMessageDialog(this, "Sesión registrada exitosamente.");
            dispose();
            new VentanaMenuUsuario(usuario, usuarios).setVisible(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Campos numéricos inválidos.");
        }
    }
}
