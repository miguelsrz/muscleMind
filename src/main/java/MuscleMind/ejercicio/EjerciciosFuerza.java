
package musclemind.ejercicio;

import musclemind.util.Medible;
import musclemind.media.Video;

public abstract class EjerciciosFuerza extends Ejercicio implements Medible {
    protected int cantidadRepeticiones;
    protected int cantidadSeries;
    protected boolean esAlFallo;
    protected Musculo musculoTrabajado;
    protected int tiempoDescanso;

    public EjerciciosFuerza(String nombre, String descripcion, int duracion, Video video,
                            int rep, int series, boolean alFallo, Musculo musculo, int descanso) {
        super(nombre, descripcion, duracion, video);
        this.cantidadRepeticiones = rep;
        this.cantidadSeries = series;
        this.esAlFallo = alFallo;
        this.musculoTrabajado = musculo;
        this.tiempoDescanso = descanso;
    }

    @Override
    public int calcularEsfuerzo() {
        return cantidadRepeticiones * cantidadSeries * (esAlFallo ? 2 : 1);
    }
}
