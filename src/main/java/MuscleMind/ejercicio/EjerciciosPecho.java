
package musclemind.ejercicio;

import musclemind.media.Video;

public class EjerciciosPecho extends EjerciciosFuerza {
    public EjerciciosPecho(String nombre, String descripcion, int duracion, Video video,
                           int rep, int series, boolean alFallo, int descanso) {
        super(nombre, descripcion, duracion, video, rep, series, alFallo, Musculo.PECHO, descanso);
    }

    @Override public void mostrarVideo() { video.verVideo(); }
    @Override public void realizar() {}
    @Override public void mostrarTiempo() {}
}
