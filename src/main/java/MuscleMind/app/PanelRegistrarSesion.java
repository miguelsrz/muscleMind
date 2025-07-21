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

        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(Color.DARK_GRAY);
        setContentPane(fondo);

        // Panel superior: selección de rutina
        JPanel panelSuperior = new JPanel(new GridLayout(2, 1));
        panelSuperior.setBackground(Color.DARK_GRAY);

        JLabel labelRutina = new JLabel("Selecciona una rutina:");
        labelRutina.setForeground(Color.WHITE);

        comboRutinas = new JComboBox<>(usuario.getListaRutinas().toArray(new Rutina[0]));
        comboRutinas.addActionListener(e -> actualizarPanelEjercicios());

        panelSuperior.add(labelRutina);
        panelSuperior.add(comboRutinas);

        // Panel ejercicios (scrollable)
        panelEjercicios = new JPanel();
        panelEjercicios.setLayout(new BoxLayout(panelEjercicios, BoxLayout.Y_AXIS));
        panelEjercicios.setBackground(Color.GRAY);
        JScrollPane scroll = new JScrollPane(panelEjercicios);

        // Panel inferior: duración + botones
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.Y_AXIS));
        panelInferior.setBackground(Color.DARK_GRAY);

        JPanel panelDuracion = new JPanel(new GridLayout(1, 2));
        panelDuracion.setBackground(Color.DARK_GRAY);

        JLabel labelDuracion = new JLabel("Duración total de la sesión (min):");
        labelDuracion.setForeground(Color.WHITE);
        campoDuracionTotal = new JTextField();

        panelDuracion.add(labelDuracion);
        panelDuracion.add(campoDuracionTotal);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(Color.DARK_GRAY);

        JButton botonRegistrar = new JButton("Registrar sesión");
        botonRegistrar.addActionListener(this::registrarSesion);

        JButton botonVolver = new JButton("Volver");
        botonVolver.addActionListener(e -> {
            dispose();
            new VentanaMenuUsuario(usuario, usuarios).setVisible(true);
        });

        panelBotones.add(botonRegistrar);
        panelBotones.add(botonVolver);

        // Ensamble
        panelInferior.add(panelDuracion);
        panelInferior.add(panelBotones);

        fondo.add(panelSuperior, BorderLayout.NORTH);
        fondo.add(scroll, BorderLayout.CENTER);
        fondo.add(panelInferior, BorderLayout.SOUTH);

        actualizarPanelEjercicios();
    }

    private void actualizarPanelEjercicios() {
        panelEjercicios.removeAll();
        camposPorEjercicio = new ArrayList<>();

        Rutina rutina = (Rutina) comboRutinas.getSelectedItem();
        if (rutina != null) {
            JLabel resumen = new JLabel("Rutina: " + rutina.getNombreRutina() + " | Total ejercicios: " + rutina.getListaEjercicios().size());
            resumen.setForeground(Color.WHITE);
            panelEjercicios.add(resumen);

            for (Ejercicio e : rutina.getListaEjercicios()) {
                JPanel fila = new JPanel();
                fila.setLayout(new BoxLayout(fila, BoxLayout.Y_AXIS));
                fila.setBackground(Color.LIGHT_GRAY);
                fila.setBorder(BorderFactory.createTitledBorder(null, e.getNombreEjercicio(),
                        0, 0, new Font("SansSerif", Font.BOLD, 14)));

                ArrayList<JTextField> campos = new ArrayList<>();

                if (e instanceof EjerciciosFuerza fuerza) {
                    for (int i = 1; i <= fuerza.getCantidadSeries(); i++) {
                        JPanel filaSerie = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        filaSerie.setBackground(Color.LIGHT_GRAY);

                        filaSerie.add(new JLabel("Serie " + i + " - Peso (kg):"));
                        JTextField peso = new JTextField(5);
                        campos.add(peso);
                        filaSerie.add(peso);

                        filaSerie.add(new JLabel("Repeticiones:"));
                        JTextField rep = new JTextField(5);
                        campos.add(rep);
                        filaSerie.add(rep);

                        fila.add(filaSerie);
                    }

                    JPanel descansoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    descansoPanel.setBackground(Color.LIGHT_GRAY);
                    descansoPanel.add(new JLabel("Descanso promedio (s):"));
                    JTextField descanso = new JTextField(5);
                    campos.add(descanso);
                    descansoPanel.add(descanso);
                    fila.add(descansoPanel);

                } else if (e instanceof EjerciciosCardio) {
                    fila.add(crearCampo("Intensidad (km/h):", campos));
                    fila.add(crearCampo("Duración (min):", campos));
                    fila.add(crearCampo("Frec. cardiaca promedio:", campos));
                } else if (e instanceof EjerciciosEstiramiento) {
                    fila.add(crearCampo("Duración (min):", campos));
                }

                panelEjercicios.add(fila);
                camposPorEjercicio.add(campos);
            }
        }

        panelEjercicios.revalidate();
        panelEjercicios.repaint();
    }

    private JPanel crearCampo(String label, ArrayList<JTextField> campos) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(new JLabel(label));
        JTextField campo = new JTextField(10);
        campos.add(campo);
        panel.add(campo);
        return panel;
    }

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
