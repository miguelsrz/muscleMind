package musclemind.app;

import musclemind.rutina.Rutina;
import musclemind.usuario.Usuario;
import musclemind.ejercicio.Ejercicio;

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

        JLabel titulo = new JLabel("Progreso de tus rutinas");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(10, 10));
        panelCentro.setBackground(new Color(30, 30, 30));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        comboRutinas = new JComboBox<>(usuario.getListaRutinas().toArray(new Rutina[0]));
        comboRutinas.setFont(new Font("SansSerif", Font.PLAIN, 14));
        comboRutinas.addActionListener(this::actualizarConsola);
        panelCentro.add(comboRutinas, BorderLayout.NORTH);

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
            sb.append("Duración: ").append(rutina.getDuraciones().get(i)).append(" min | ")
                    .append("Calorías: ").append(rutina.getCalorias().get(i)).append(" kcal\n");

            sb.append("\nEjercicios realizados:\n");

            ArrayList<ArrayList<String>> resultadosEj = resultadosPorSesion.get(i);

            for (int j = 0; j < ejercicios.size(); j++) {
                Ejercicio ej = ejercicios.get(j);
                sb.append("  • ").append(ej.getNombreEjercicio()).append(" - ")
                        .append(ej.getDescripcionEjercicio()).append("\n");

                if (j < resultadosEj.size()) {
                    ArrayList<String> series = resultadosEj.get(j);

                    if (series.isEmpty()) {
                        sb.append("     Sin datos registrados\n");
                        continue;
                    }

                    for (int s = 0; s < series.size(); s++) {
                        String dato = series.get(s).trim();
                        if (dato.isEmpty()) {
                            sb.append("     Serie ").append(s + 1).append(": No se registraron datos.\n");
                        } else {
                            String[] valores = dato.split(",");
                            if (valores.length == 2) {
                                sb.append("     Serie ").append(s + 1)
                                        .append(": Peso = ").append(valores[0].trim())
                                        .append(" kg | Repeticiones = ").append(valores[1].trim()).append("\n");
                            } else {
                                sb.append("     Serie ").append(s + 1).append(": ").append(dato).append("\n");
                            }
                        }
                    }

                } else {
                    sb.append("     Sin datos registrados\n");
                }
            }
        }

        if (rutina.getFechasSesiones().isEmpty()) {
            sb.append("\nAún no se han registrado sesiones para esta rutina.\n");
        }

        areaConsola.setText(sb.toString());
    }

}
