
package musclemind.usuario;

import musclemind.ejercicio.Ejercicio;
import java.time.LocalDateTime;
import java.util.List;

public class Sesion {
    private String rutina;
    private LocalDateTime fecha;
    private int duracionReal;
    private int caloriasTotales;
    private List<Ejercicio> ejerciciosRealizados;

    public Sesion(String rutina, LocalDateTime fecha, int duracionReal, int caloriasTotales, List<Ejercicio> ejerciciosRealizados) {
        this.rutina = rutina;
        this.fecha = fecha;
        this.duracionReal = duracionReal;
        this.caloriasTotales = caloriasTotales;
        this.ejerciciosRealizados = ejerciciosRealizados;
    }

    public void mostrarResumen() {
        System.out.println("Resumen de la sesión: " + rutina);
        System.out.println("Duración: " + duracionReal + " min");
        System.out.println("Calorías: " + caloriasTotales);
    }
}
