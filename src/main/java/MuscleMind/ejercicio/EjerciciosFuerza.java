
package musclemind.ejercicio;

import musclemind.media.Video;
import musclemind.util.Medible;

public abstract class EjerciciosFuerza extends Ejercicio implements Medible {
    protected int cantidadRepeticiones; // MAXIMAS en una serie
    protected int cantidadSeries;
    protected boolean esAlFallo; // Permite entender al usuario el objetivo del ejercicio
    protected Musculo musculoTrabajado;
    protected int tiempoDescanso; // en segundos

    public EjerciciosFuerza(String nombre, String descripcion, int duracion, Video video,
                            int repeticiones, int series, boolean alFallo, Musculo musculo, int descanso) {
        super(nombre, descripcion, duracion, video);
        this.cantidadRepeticiones = repeticiones;
        this.cantidadSeries = series;
        this.esAlFallo = alFallo;
        this.musculoTrabajado = musculo;
        this.tiempoDescanso = descanso;
    }

    // Getters
    public int getCantidadRepeticiones() { return cantidadRepeticiones; }
    public int getCantidadSeries() { return cantidadSeries; }
    public boolean esAlFallo() { return esAlFallo; }
    public Musculo getMusculoTrabajado() { return musculoTrabajado; }
    public int getTiempoDescanso() { return tiempoDescanso; }
    
    
    // Se espera implementar cuando se vea interfaz
    @Override
    public int calcularEsfuerzo() {
        return cantidadRepeticiones * cantidadSeries * (esAlFallo ? 2 : 1);
    }
}
