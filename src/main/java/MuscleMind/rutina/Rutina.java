
package musclemind.rutina;

import musclemind.ejercicio.Ejercicio;
import musclemind.ejercicio.EjerciciosFuerza;
import musclemind.ejercicio.EjerciciosCardio;
import java.util.ArrayList;
import java.util.List;

public class Rutina {
    private String nombreRutina;
    private List<EjerciciosFuerza> listaEjerciciosFuerza;
    private List<EjerciciosCardio> listaEjerciciosCardio;

    public Rutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
        this.listaEjerciciosFuerza = new ArrayList<>();
        this.listaEjerciciosCardio = new ArrayList<>();
    }

    public String getNombreRutina() { return nombreRutina; }
    public List<EjerciciosFuerza> getEjerciciosFuerza() { return listaEjerciciosFuerza; }
    public List<EjerciciosCardio> getEjerciciosCardio() { return listaEjerciciosCardio; }

    public void agregarEjercicio(Ejercicio e) {
        if (e instanceof EjerciciosFuerza fuerza) {
            listaEjerciciosFuerza.add(fuerza);
        } else if (e instanceof EjerciciosCardio cardio) {
            listaEjerciciosCardio.add(cardio);
        }
    }

    public void mostrarResumen() {
        System.out.println("Rutina: " + nombreRutina);
        System.out.println("Fuerza: " + listaEjerciciosFuerza.size() + " ejercicios");
        System.out.println("Cardio: " + listaEjerciciosCardio.size() + " ejercicios");
    }
}
