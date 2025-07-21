// Aplicacion de consola que hace demostracion del flujo esperado de un usuario al implementar interfaz grafica
// Se utilizan mayoria de clases y sus metodos, sin embargo, se espera ampliar en funcionalidades y la calidad de ellas cuando se implemente interfaz grafics
package musclemind.app;

// Imports necesarios
import java.time.LocalDateTime;
import musclemind.usuario.*;
import musclemind.rutina.*;
import musclemind.media.*;
import musclemind.ejercicio.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        // Aquí deberías cargar usuarios desde archivo si ya tienes eso implementado
        // Por ahora puedes crear uno manualmente:
        usuarios.add(new Usuario("miguelsrz", 1234, 19, 70, "Hipertrofia", Rutina.generarRutinaPorObjetivo("Hipertrofia")));

        new VentanaPrincipal(usuarios).setVisible(true);
    }
}