
package musclemind.usuario;

import musclemind.rutina.Rutina;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombreUsuario;
    private int edad;
    private int pesoKg;
    private List<Rutina> listaRutinas = new ArrayList<>();

    public Usuario(String nombreUsuario, int edad, int pesoKg) {
        this.nombreUsuario = nombreUsuario;
        this.edad = edad;
        this.pesoKg = pesoKg;
    }

    public String getNombreUsuario() { return nombreUsuario; }
    public int getEdad() { return edad; }
    public int getPesoKg() { return pesoKg; }
    public List<Rutina> getListaRutinas() { return listaRutinas; }

    public void agregarRutina(Rutina rutina) {
        listaRutinas.add(rutina);
    }

    public void empezarRutina(String nombreRutina) {
        System.out.println(nombreUsuario + " ha comenzado la rutina: " + nombreRutina);
    }

    public void terminarRutina(String nombreRutina) {
        System.out.println(nombreUsuario + " ha terminado la rutina: " + nombreRutina);
    }
}
