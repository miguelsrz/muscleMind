
package musclemind.usuario;

import musclemind.rutina.Rutina;
import java.util.ArrayList;

public class Usuario {
    private String nombreUsuario;
    private int edad;
    private int pesoKg;
    private ArrayList<Rutina> listaRutinas = new ArrayList<>();

    public Usuario(String nombre, int edad, int peso) {
        this.nombreUsuario = nombre;
        this.edad = edad;
        this.pesoKg = peso;
    }

    public void agregarRutina(Rutina rutina) {
        listaRutinas.add(rutina);
    }

    public void empezarRutina() {
        System.out.println(nombreUsuario + " ha comenzado una rutina.");
    }

    public void terminarRutina() {
        System.out.println(nombreUsuario + " ha terminado la rutina.");
    }
}
