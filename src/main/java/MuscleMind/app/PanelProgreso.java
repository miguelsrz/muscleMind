package musclemind.app;

import musclemind.rutina.*;
import musclemind.usuario.*;
import musclemind.ejercicio.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PanelProgreso extends JFrame {

    private Usuario usuario;
    private ArrayList<Usuario> usuarios;
    private JComboBox<Rutina> comboRutinas;
    private JTextArea areaConsola;

    public PanelProgreso(Usuario usuario, ArrayList<Usuario> usuarios) {
        this.usuario = usuario;
        this.usuarios = usuarios;

        setTitle("MuscleMind - Progreso");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));
        
        // Parte superior
        JLabel titulo = new JLabel("Progreso de tus Rutinas");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(10, 10));
        panelCentro.setBackground(new Color(30, 30, 30));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Seleccion de rutina a mostrar historico
        comboRutinas = new JComboBox<>(usuario.getListaRutinas().toArray(new Rutina[0]));
        comboRutinas.setFont(new Font("SansSerif", Font.PLAIN, 14));
        comboRutinas.addActionListener(this::actualizarConsola);
        panelCentro.add(comboRutinas, BorderLayout.NORTH);
        
        // Area tipo consola que muestra la informacion de las distintas sesiones
        areaConsola = new JTextArea();
        areaConsola.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaConsola.setEditable(false);
        areaConsola.setBackground(new Color(40, 40, 40));
        areaConsola.setForeground(Color.WHITE);
        areaConsola.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(areaConsola);
        scroll.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panelCentro.add(scroll, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.CENTER);

        JButton volver = new JButton("Volver al menú");
        volver.setFont(new Font("SansSerif", Font.BOLD, 14));
        volver.setBackground(new Color(70, 130, 180));
        volver.setForeground(Color.WHITE);
        volver.setPreferredSize(new Dimension(160, 40));
        volver.addActionListener(e -> {
            dispose();
            new VentanaMenuUsuario(usuario, usuarios).setVisible(true);
        });

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(30, 30, 30));
        panelBoton.add(volver);
        add(panelBoton, BorderLayout.SOUTH);

        if (comboRutinas.getItemCount() > 0) {
            comboRutinas.setSelectedIndex(0);
            actualizarConsola(null);
        }
    }
    
    // Cuando se cambia el ejercicio se actualiza la consola con la informacion. Se muestran TODAS las sesiones, ya formateado, con todo lo necesario
    private void actualizarConsola(ActionEvent evt) {
        Rutina rutina = (Rutina) comboRutinas.getSelectedItem();
        if (rutina == null) {
            areaConsola.setText("No hay rutina seleccionada.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy, HH:mm");

        ArrayList<ArrayList<ArrayList<String>>> resultadosPorSesion = rutina.getResultadosPorSesion();
        ArrayList<Ejercicio> ejercicios = rutina.getListaEjercicios();

        sb.append(">> PROGRESO DE LA RUTINA: ").append(rutina.getNombreRutina()).append("\n");

        for (int i = 0; i < rutina.getFechasSesiones().size(); i++) {
            sb.append("\n").append("-".repeat(58)).append("\n");
            sb.append("Sesión #").append(i + 1).append(" - ")
                    .append(rutina.getFechasSesiones().get(i).format(formatter)).append("\n");
            sb.append("Duración: ").append(rutina.getDuraciones().get(i)).append(" min\n");

            sb.append("\nEjercicios realizados:\n");

            ArrayList<ArrayList<String>> resultadosEj = resultadosPorSesion.get(i);

            for (int j = 0; j < ejercicios.size(); j++) {
                Ejercicio ej = ejercicios.get(j);
                sb.append("  • ").append(ej.getNombreEjercicio()).append(" - ")
                        .append(ej.getDescripcionEjercicio()).append("\n");

                if (j >= resultadosEj.size()) {
                    sb.append("     Sin datos registrados\n");
                    continue;
                }

                ArrayList<String> datos = resultadosEj.get(j);
                if (datos.isEmpty()) {
                    sb.append("     Sin datos registrados\n");
                    continue;
                }

                if (ej instanceof EjerciciosFuerza) {
                    int limiteSeries = datos.size() - 1; // el último es el descanso
                    int cantidadSeries = (limiteSeries) / 2;

                    for (int s = 0; s < cantidadSeries; s++) {
                        int idxPeso = 2 * s;
                        int idxReps = idxPeso + 1;

                        String peso = datos.get(idxPeso).trim();
                        String reps = (idxReps < datos.size()) ? datos.get(idxReps).trim() : "";

                        if (peso.isEmpty() && reps.isEmpty()) {
                            sb.append("     Serie ").append(s + 1).append(": No se registraron datos.\n");
                        } else {
                            sb.append("     Serie ").append(s + 1)
                                    .append(": Peso = ").append(peso.isEmpty() ? "N/A" : peso + " kg")
                                    .append(" | Repeticiones = ").append(reps.isEmpty() ? "N/A" : reps)
                                    .append("\n");
                        }
                    }

                    // Mostrar descanso promedio
                    String descanso = datos.get(datos.size() - 1).trim();
                    sb.append("     Descanso promedio: ").append(descanso.isEmpty() ? "N/A" : descanso + " s").append("\n");

                } else if (ej instanceof EjerciciosCardio) {
                    String intensidad = datos.size() > 0 ? datos.get(0).trim() : "";
                    String duracion = datos.size() > 1 ? datos.get(1).trim() : "";
                    String frecuencia = datos.size() > 2 ? datos.get(2).trim() : "";

                    if (intensidad.isEmpty() && duracion.isEmpty() && frecuencia.isEmpty()) {
                        sb.append("     Sin datos registrados\n");
                    } else {
                        sb.append("     Intensidad: ").append(intensidad.isEmpty() ? "N/A" : intensidad).append(" km/h\n");
                        sb.append("     Duración: ").append(duracion.isEmpty() ? "N/A" : duracion).append(" min\n");
                        sb.append("     Frec. cardiaca prom.: ").append(frecuencia.isEmpty() ? "N/A" : frecuencia).append("\n");
                    }

                } else {
                    for (String dato : datos) {
                        dato = dato.trim();
                        if (!dato.isEmpty()) {
                            sb.append("     • ").append(dato).append("\n");
                        }
                    }
                }
            }
        }

        if (rutina.getFechasSesiones().isEmpty()) {
            sb.append("\nAún no se han registrado sesiones para esta rutina.\n");
        }

        areaConsola.setText(sb.toString());
    }

}
