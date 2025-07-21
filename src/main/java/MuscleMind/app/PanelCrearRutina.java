package musclemind.app;

import musclemind.ejercicio.*;
import musclemind.rutina.Rutina;
import musclemind.usuario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class PanelCrearRutina extends JFrame {

    private JTextField txtNombreRutina;
    private JTextField txtNombreEjercicio;
    private JComboBox<String> comboMusculo;
    private JSpinner spDuracion, spSeries, spReps, spDescanso;
    private JCheckBox chkAlFallo;
    private DefaultListModel<Ejercicio> modeloLista;
    private JList<Ejercicio> lista;

    private Usuario usuario;
    private ArrayList<Usuario> usuarios;

    public PanelCrearRutina(Usuario usuario, ArrayList<Usuario> usuarios) {
        this.usuario = usuario;
        this.usuarios = usuarios;

        setTitle("MuscleMind - Crear Rutina");
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

        JLabel lblTitulo = new JLabel("Crear Nueva Rutina", JLabel.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 5, 10));

        JLabel descripcion = new JLabel("Agrega ejercicios a tu rutina personalizada.");
        descripcion.setFont(new Font("SansSerif", Font.PLAIN, 14));
        descripcion.setForeground(Color.LIGHT_GRAY);
        descripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
        descripcion.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        encabezado.add(lblTitulo);
        encabezado.add(descripcion);
        fondo.add(encabezado, BorderLayout.NORTH);

        // --- CENTRO ---
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(new Color(30, 30, 30));

        // Panel formulario con scroll
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(new Color(60, 60, 60)); // gris más claro

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 16, 10, 16); // más espaciado horizontal
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        int fila = 0;

        gbc.gridx = 0;
        gbc.gridy = fila;
        panelFormulario.add(etiqueta("Nombre Rutina:"), gbc);
        txtNombreRutina = crearCampoTexto();
        gbc.gridx = 1;
        panelFormulario.add(txtNombreRutina, gbc);
        fila++;

        gbc.gridx = 0;
        gbc.gridy = fila;
        panelFormulario.add(etiqueta("Nombre Ejercicio:"), gbc);
        txtNombreEjercicio = crearCampoTexto();
        gbc.gridx = 1;
        panelFormulario.add(txtNombreEjercicio, gbc);
        fila++;

        gbc.gridx = 0;
        gbc.gridy = fila;
        panelFormulario.add(etiqueta("Músculo:"), gbc);
        comboMusculo = new JComboBox<>(new String[]{"Pecho", "Espalda", "Pierna", "Hombro", "Biceps", "Triceps", "Abdomen"});
        gbc.gridx = 1;
        panelFormulario.add(comboMusculo, gbc);
        fila++;

        gbc.gridx = 0;
        gbc.gridy = fila;
        panelFormulario.add(etiqueta("Duración (seg):"), gbc);
        spDuracion = new JSpinner(new SpinnerNumberModel(0, 0, 3600, 10));
        gbc.gridx = 1;
        panelFormulario.add(spDuracion, gbc);
        fila++;

        gbc.gridx = 0;
        gbc.gridy = fila;
        panelFormulario.add(etiqueta("Series:"), gbc);
        spSeries = new JSpinner(new SpinnerNumberModel(3, 1, 20, 1));
        gbc.gridx = 1;
        panelFormulario.add(spSeries, gbc);
        fila++;

        gbc.gridx = 0;
        gbc.gridy = fila;
        panelFormulario.add(etiqueta("Reps:"), gbc);
        spReps = new JSpinner(new SpinnerNumberModel(10, 1, 100, 1));
        gbc.gridx = 1;
        panelFormulario.add(spReps, gbc);
        fila++;

        gbc.gridx = 0;
        gbc.gridy = fila;
        panelFormulario.add(etiqueta("Descanso (seg):"), gbc);
        spDescanso = new JSpinner(new SpinnerNumberModel(60, 10, 600, 5));
        gbc.gridx = 1;
        panelFormulario.add(spDescanso, gbc);
        fila++;

        gbc.gridx = 0;
        gbc.gridy = fila;
        panelFormulario.add(etiqueta("Al fallo:"), gbc);
        chkAlFallo = new JCheckBox("Sí");
        chkAlFallo.setBackground(new Color(60, 60, 60));
        chkAlFallo.setForeground(Color.WHITE);
        gbc.gridx = 1;
        panelFormulario.add(chkAlFallo, gbc);
        fila++;

        JScrollPane scrollFormulario = new JScrollPane(panelFormulario);
        scrollFormulario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 280));
        scrollFormulario.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollFormulario.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Datos del ejercicio", 0, 0, null, Color.BLACK));

        panelCentro.add(scrollFormulario);

        // Botón Agregar Ejercicio (más espacio y centrado)
        JPanel panelBotonAgregar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotonAgregar.setBackground(new Color(60, 60, 60)); // mismo gris del fondo del formulario

        panelBotonAgregar.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // más espacio arriba y abajo

        JButton btnAgregar = new JButton("Agregar Ejercicio");
        estilizarBoton(btnAgregar, new Color(70, 130, 180));
        btnAgregar.setPreferredSize(new Dimension(200, 40));
        panelBotonAgregar.add(btnAgregar);

        panelCentro.add(panelBotonAgregar);

        // Lista de ejercicios agregados
        modeloLista = new DefaultListModel<>();
        lista = new JList<>(modeloLista);
        lista.setFont(new Font("Monospaced", Font.PLAIN, 13));
        lista.setBackground(new Color(45, 45, 45));
        lista.setForeground(Color.LIGHT_GRAY);
        lista.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Ejercicios en la Rutina", 0, 0, null, Color.WHITE));
        lista.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Ejercicio e) {
                    String texto = String.format("• %s [%d reps x %d series | %ds descanso]%s",
                            e.getNombreEjercicio(),
                            (e instanceof EjerciciosFuerza ef) ? ef.getCantidadRepeticiones() : 0,
                            (e instanceof EjerciciosFuerza ef) ? ef.getCantidadSeries() : 0,
                            (e instanceof EjerciciosFuerza ef) ? ef.getTiempoDescanso() : 0,
                            (e instanceof EjerciciosFuerza ef && ef.esAlFallo()) ? " al fallo" : "");
                    label.setText(texto);
                }
                return label;
            }
        });

        JScrollPane scrollLista = new JScrollPane(lista);
        scrollLista.setPreferredSize(new Dimension(250, 200));
        scrollLista.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentro.add(scrollLista);

        fondo.add(panelCentro, BorderLayout.CENTER);

        // --- BOTONES INFERIORES ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        panelBotones.setBackground(new Color(30, 30, 30));

        JButton btnGuardar = new JButton("Guardar Rutina");
        estilizarBoton(btnGuardar, new Color(70, 130, 180));
        btnGuardar.setPreferredSize(new Dimension(160, 50));

        JButton btnEliminar = new JButton("Eliminar Ejercicio");
        estilizarBoton(btnEliminar, Color.GRAY);
        btnEliminar.setPreferredSize(new Dimension(160, 50));

        JButton btnCerrar = new JButton("Volver al Menú");
        estilizarBoton(btnCerrar, Color.DARK_GRAY);
        btnCerrar.setPreferredSize(new Dimension(160, 50));

        panelBotones.add(btnGuardar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);

        fondo.add(panelBotones, BorderLayout.SOUTH);
        add(fondo);

        // --- EVENTOS ---
        btnAgregar.addActionListener(e -> agregarEjercicio());
        btnEliminar.addActionListener(e -> {
            int idx = lista.getSelectedIndex();
            if (idx >= 0) {
                modeloLista.remove(idx);
            }
        });
        btnGuardar.addActionListener(e -> guardarRutina());
        btnCerrar.addActionListener(e -> {
            dispose();
            new VentanaMenuUsuario(usuario, usuarios).setVisible(true);
        });
    }

    private JLabel etiqueta(String txt) {
        JLabel l = new JLabel(txt);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("SansSerif", Font.BOLD, 13));
        return l;
    }

    private JTextField crearCampoTexto() {
        JTextField campo = new JTextField();
        campo.setPreferredSize(new Dimension(200, 28));
        campo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        campo.setHorizontalAlignment(JTextField.CENTER);
        return campo;
    }

    private void estilizarBoton(JButton boton, Color colorFondo) {
        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
    }

    private void agregarEjercicio() {
        String nombre = txtNombreEjercicio.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre ejercicio vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int dur = (Integer) spDuracion.getValue();
        int reps = (Integer) spReps.getValue();
        int series = (Integer) spSeries.getValue();
        int descanso = (Integer) spDescanso.getValue();
        boolean alFallo = chkAlFallo.isSelected();
        String musc = Objects.toString(comboMusculo.getSelectedItem(), "Pecho");

        Ejercicio ej;
        switch (musc) {
            case "Pecho" ->
                ej = new EjerciciosPecho(nombre, "", dur, null, reps, series, alFallo, descanso);
            case "Espalda" ->
                ej = new EjerciciosEspalda(nombre, "", dur, null, reps, series, alFallo, descanso);
            case "Pierna" ->
                ej = new EjerciciosPierna(nombre, "", dur, null, reps, series, alFallo, descanso);
            case "Hombro", "Biceps", "Triceps" ->
                ej = new EjerciciosBrazo(nombre, "", dur, null, reps, series, alFallo, descanso);
            case "Abdomen" ->
                ej = new EjerciciosCore(nombre, "", dur, null, reps, series, alFallo, descanso);
            default ->
                ej = new EjerciciosPecho(nombre, "", dur, null, reps, series, alFallo, descanso);
        }

        modeloLista.addElement(ej);
        txtNombreEjercicio.setText("");
    }

    private void guardarRutina() {
        String nombreRutina = txtNombreRutina.getText().trim();
        if (nombreRutina.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre de rutina vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (modeloLista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agrega al menos un ejercicio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Rutina nuevaRutina = new Rutina(nombreRutina);
        for (int i = 0; i < modeloLista.size(); i++) {
            nuevaRutina.agregarEjercicio(modeloLista.get(i));
        }

        usuario.agregarRutina(nuevaRutina);

        JOptionPane.showMessageDialog(this,
                "Rutina guardada con éxito.\nTotal ejercicios: " + modeloLista.size(),
                "Rutina Creada", JOptionPane.INFORMATION_MESSAGE);

        dispose();
        new VentanaMenuUsuario(usuario, usuarios).setVisible(true);
    }
}
