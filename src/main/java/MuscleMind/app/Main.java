package musclemind.app;

// Imports necesarios
import musclemind.usuario.*;
import musclemind.rutina.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        // Cuentas predeterminadas ya creadas
        usuarios.add(new Usuario("miguelsrz", 1234, 19, 70, "Hipertrofia", Rutina.generarRutinaPorObjetivo("Hipertrofia")));
        usuarios.add(new Usuario("josuecav", 1234, 20, 75, "Resistencia", Rutina.generarRutinaPorObjetivo("Resistencia")));
        usuarios.add(new Usuario("cparadae", 1234, 18, 72, "Definicion", Rutina.generarRutinaPorObjetivo("Definicion")));

        new VentanaPrincipal(usuarios).setVisible(true);
    }
}