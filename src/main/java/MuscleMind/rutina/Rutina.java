
package musclemind.rutina;

import musclemind.ejercicio.Ejercicio;
import musclemind.ejercicio.EjerciciosFuerza;
import musclemind.ejercicio.EjerciciosCardio;
import java.util.ArrayList;

public class Rutina {
    private String nombreRutina;
    private ArrayList<EjerciciosFuerza> listaEjerciciosFuerza = new ArrayList<>();
    private ArrayList<EjerciciosCardio> listaEjerciciosCardio = new ArrayList<>();

    public Rutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
    }

    public void agregarEjercicio(Ejercicio e) {
        if (e instanceof EjerciciosFuerza) {
            listaEjerciciosFuerza.add((EjerciciosFuerza) e);
        } else if (e instanceof EjerciciosCardio) {
            listaEjerciciosCardio.add((EjerciciosCardio) e);
        }
    }

    public void mostrarResumen() {
        System.out.println("Rutina: " + nombreRutina);
        System.out.println("Ejercicios de Fuerza: " + listaEjerciciosFuerza.size());
        System.out.println("Ejercicios de Cardio: " + listaEjerciciosCardio.size());
    }
}
