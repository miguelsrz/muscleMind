package musclemind.ejercicio;

import musclemind.media.Video;
import musclemind.util.Medible;

public class EjerciciosCardio extends Ejercicio implements Medible {
    private int caloriasQuemadas; // Se espera obtener con calculos en un futuro con interfaz
    private int pulsacionesPromedio;

    public EjerciciosCardio(String nombre, String descripcion, int duracion, Video video,
                            int calorias, int pulsaciones) {
        super(nombre, descripcion, duracion, video);
        this.caloriasQuemadas = calorias;
        this.pulsacionesPromedio = pulsaciones;
    }

    // Getters
    public int getCaloriasQuemadas() { return caloriasQuemadas; }
    public int getPulsacionesPromedio() { return pulsacionesPromedio; }
    
    // Se esperan utilizar cuando se vean interfaz
    @Override public void mostrarVideo() { video.verVideo(); }
    @Override public void realizar() {
        System.out.println("Realizando ejercicio de cardio: " + nombreEjercicio);
    }
    @Override public void mostrarTiempo() {
        System.out.println("Duración estimada: " + duracion + " segundos.");
    }
    @Override public int calcularEsfuerzo() { return caloriasQuemadas; }
}