package musclemind.usuario;

import musclemind.rutina.Rutina;
import java.util.ArrayList;

public class Usuario {

    private String nombreUsuario;
    private int edad;
    private int pesoKg;
    private int password;
    private String objetivo;
    private ArrayList<Rutina> listaRutinas;

    public Usuario(String nombreUsuario, int password, int edad, int pesoKg, String objetivo, Rutina rutina) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.edad = edad;
        this.pesoKg = pesoKg;
        this.objetivo = objetivo;
        this.listaRutinas = new ArrayList<>();
        listaRutinas.add(rutina);
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public int getPassword() {
        return password;
    }

    public int getEdad() {
        return edad;
    }

    public int getPesoKg() {
        return pesoKg;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public void agregarRutina(Rutina rutina) {
        listaRutinas.add(rutina);
    }

    public void eliminarRutina(Rutina rutina) {
        listaRutinas.remove(rutina);
    }

    public ArrayList<Rutina> getListaRutinas() {
        return listaRutinas;
    }

    public void empezarRutina(String nombreRutina) {
        System.out.println(nombreUsuario + " ha comenzado la rutina: " + nombreRutina);
    }

    public void terminarRutina(String nombreRutina) {
        System.out.println(nombreUsuario + " ha terminado la rutina: " + nombreRutina);
    }
}
