
package musclemind.ejercicio;

import musclemind.media.Video;
import musclemind.util.Medible;

public class EjerciciosCardio extends Ejercicio implements Medible {
    private int caloriasQuemadas;
    private int pulsacionesPromedio;

    public EjerciciosCardio(String nombre, String descripcion, int duracion, Video video,
                            int calorias, int pulsaciones) {
        super(nombre, descripcion, duracion, video);
        this.caloriasQuemadas = calorias;
        this.pulsacionesPromedio = pulsaciones;
    }

    @Override public void mostrarVideo() { video.verVideo(); }
    @Override public void realizar() {}
    @Override public void mostrarTiempo() {}
    @Override public int calcularEsfuerzo() { return caloriasQuemadas; }
}
