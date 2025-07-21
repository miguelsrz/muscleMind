package musclemind.app;

import musclemind.rutina.Rutina;
import musclemind.usuario.Usuario;
import musclemind.ejercicio.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaVerRutinas extends JFrame {

    private Usuario usuario;
    private ArrayList<Usuario> usuarios;
    private DefaultListModel<Rutina> modeloRutinas;
    private JList<Rutina> listaRutinas;
    private JTextArea areaDetalles;

    public VentanaVerRutinas(Usuario usuario, ArrayList<Usuario> usuarios) {
        this.usuario = usuario;
        this.usuarios = usuarios;

        setTitle("MuscleMind - Ver Rutinas");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel fondo = new JPanel(new BorderLayout(10, 10));
        fondo.setBackground(new Color(30, 30, 30));
        fondo.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // --- ENCABEZADO ---
        JPanel encabezado = new JPanel();
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.Y_AXIS));
        encabezado.setBackground(new Color(30, 30, 30));

        JLabel titulo = new JLabel("Tus Rutinas Guardadas", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descripcion = new JLabel("Aquí puedes consultar o eliminar tus rutinas personalizadas.");
        descripcion.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descripcion.setForeground(Color.LIGHT_GRAY);
        descripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
        descripcion.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));

        encabezado.add(titulo);
        encabezado.add(descripcion);
        fondo.add(encabezado, BorderLayout.NORTH);

        // --- MODELO DE RUTINAS ---
        modeloRutinas = new DefaultListModel<>();
        for (Rutina r : usuario.getListaRutinas()) {
            modeloRutinas.addElement(r);
        }

        listaRutinas = new JList<>(modeloRutinas);
        listaRutinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaRutinas.setFont(new Font("SansSerif", Font.PLAIN, 14));
        listaRutinas.setBackground(new Color(60, 63, 65));
        listaRutinas.setForeground(Color.WHITE);
        listaRutinas.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Rutina rutina) {
                    label.setText(rutina.getNombreRutina());
                }
                return label;
            }
        });

        JScrollPane scrollRutinas = new JScrollPane(listaRutinas);
        scrollRutinas.setBorder(BorderFactory.createTitledBorder("Selecciona una rutina"));
        scrollRutinas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        areaDetalles = new JTextArea();
        areaDetalles.setEditable(false);
        areaDetalles.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaDetalles.setBackground(new Color(45, 45, 45));
        areaDetalles.setForeground(Color.LIGHT_GRAY);
        areaDetalles.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY),
                "Ejercicios en la Rutina", 0, 0, null, Color.WHITE));

        JScrollPane scrollDetalles = new JScrollPane(areaDetalles);
        scrollDetalles.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));

        // --- CENTRO ---
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(new Color(30, 30, 30));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        panelCentro.add(scrollRutinas);
        panelCentro.add(Box.createVerticalStrut(15));
        panelCentro.add(scrollDetalles);

        fondo.add(panelCentro, BorderLayout.CENTER);

        // --- PIE DE PÁGINA ---
        JPanel pie = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        pie.setBackground(new Color(30, 30, 30));

        JButton btnEliminar = new JButton("Eliminar Rutina");
        JButton btnVolver = new JButton("Volver al menú");

        configurarBoton(btnEliminar, new Color(178, 34, 34)); // rojo fuerte
        configurarBoton(btnVolver, Color.GRAY);

        pie.add(btnEliminar);
        pie.add(btnVolver);
        fondo.add(pie, BorderLayout.SOUTH);

        // --- EVENTOS ---
        listaRutinas.addListSelectionListener(e -> mostrarDetalles());

        btnEliminar.addActionListener(e -> {
            Rutina seleccionada = listaRutinas.getSelectedValue();
            if (seleccionada == null) {
                JOptionPane.showMessageDialog(this, "Selecciona una rutina para eliminar.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que deseas eliminar la rutina '" + seleccionada.getNombreRutina() + "'?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                usuario.getListaRutinas().remove(seleccionada);
                modeloRutinas.removeElement(seleccionada);
                areaDetalles.setText("");
                JOptionPane.showMessageDialog(this, "Rutina eliminada exitosamente.", "Rutina eliminada", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnVolver.addActionListener(e -> {
            dispose();
            new VentanaMenuUsuario(usuario, usuarios).setVisible(true);
        });

        add(fondo);
    }

    private void mostrarDetalles() {
        Rutina seleccionada = listaRutinas.getSelectedValue();
        if (seleccionada == null) return;

        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(seleccionada.getNombreRutina()).append("\n");
        sb.append("Total ejercicios: ").append(seleccionada.getListaEjercicios().size()).append("\n");
        sb.append("----------------------------------------------------------\n");

        for (Ejercicio e : seleccionada.getListaEjercicios()) {
            sb.append("• ").append(e.getNombreEjercicio()).append(" (")
                    .append(tipoEjercicioLegible(e)).append(")\n")
                    .append("   ▸ Descripción: ").append(e.getDescripcionEjercicio()).append("\n");
            if (e instanceof EjerciciosFuerza) {
                sb.append("   ▸ Series: ").append(((EjerciciosFuerza) e).getCantidadSeries()).append(" | Reps: ").append(((EjerciciosFuerza) e).getCantidadRepeticiones());
                sb.append(" | Descanso: ").append(((EjerciciosFuerza) e).getTiempoDescanso()).append(" seg");
                sb.append(((EjerciciosFuerza) e).esAlFallo() ? " | Al fallo\n" : "\n");
            } else if (e instanceof EjerciciosCardio) {
                sb.append("   ▸ Duración: ").append(e.getDuracion()).append(" seg")
                        .append(" | Intensidad estimada: ").append(((EjerciciosCardio) e).getCaloriasQuemadas()).append(" cal\n");
            }
            sb.append("\n");
        }

        areaDetalles.setText(sb.toString());
    }

    private String tipoEjercicioLegible(Ejercicio e) {
        String clase = e.getClass().getSimpleName();
        if (clase.contains("Pecho")) return "Pecho";
        if (clase.contains("Espalda")) return "Espalda";
        if (clase.contains("Pierna")) return "Pierna";
        if (clase.contains("Brazo")) return "Brazo";
        if (clase.contains("Core")) return "Core";
        if (clase.contains("Cardio")) return "Cardio";
        return "General";
    }

    private void configurarBoton(JButton b, Color color) {
        b.setFont(new Font("SansSerif", Font.BOLD, 14));
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(160, 40));
    }
}
