// Las clases hijas de EjercicioFuerza actualmente se diferencian por el musculo que trabajan, cuando se implemente interfaz se diferenciaran con informacion para el usuario o relacionados

package musclemind.ejercicio;

import musclemind.media.Video;

public class EjerciciosEspalda extends EjerciciosFuerza {
    public EjerciciosEspalda(String nombre, String descripcion, int duracion, Video video,
                          int rep, int series, boolean alFallo, int descanso) {
        super(nombre, descripcion, duracion, video, rep, series, alFallo, Musculo.ESPALDA, descanso);
    }

    @Override public void mostrarVideo() { video.verVideo(); }
    @Override public void realizar() {
        System.out.println("Realizando ejercicio de ESPALDA: " + nombreEjercicio);
    }
    @Override public void mostrarTiempo() {
        System.out.println("Duración: " + duracion + " segundos");
    }
}
