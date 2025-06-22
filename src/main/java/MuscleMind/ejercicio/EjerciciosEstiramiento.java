
package musclemind.ejercicio;

import musclemind.media.Video;

public class EjerciciosEstiramiento extends Ejercicio {
    private Musculo musculoEstirado;

    public EjerciciosEstiramiento(String nombre, String descripcion, int duracion, Video video, Musculo musculo) {
        super(nombre, descripcion, duracion, video);
        this.musculoEstirado = musculo;
    }

    @Override public void mostrarVideo() { video.verVideo(); }
    @Override public void realizar() {}
    @Override public void mostrarTiempo() {}
}
