
package musclemind.usuario;

import musclemind.ejercicio.Ejercicio;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sesion {
    private String nombreRutina;
    private LocalDateTime fecha;
    private int duracionReal;
    private int caloriasTotales;
    private ArrayList<Ejercicio> ejerciciosRealizados;

    public Sesion(String nombreRutina, LocalDateTime fecha, int duracionReal, int caloriasTotales, ArrayList<Ejercicio> ejerciciosRealizados) {
        this.nombreRutina = nombreRutina;
        this.fecha = fecha;
        this.duracionReal = duracionReal;
        this.caloriasTotales = caloriasTotales;
        this.ejerciciosRealizados = ejerciciosRealizados;
    }

    public void mostrarResumen() {
        System.out.println("Resumen de sesión: " + nombreRutina);
        System.out.println("Fecha: " + fecha);
        System.out.println("Duración: " + duracionReal + " minutos");
        System.out.println("Calorías quemadas: " + caloriasTotales);
        System.out.println("Ejercicios realizados: " + ejerciciosRealizados.size());
    }
}
