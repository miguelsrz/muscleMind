
package musclemind.ejercicio;

import musclemind.media.Video;

public class EjerciciosBrazo extends EjerciciosFuerza {
    public EjerciciosBrazo(String nombre, String descripcion, int duracion, Video video,
                          int rep, int series, boolean alFallo, int descanso) {
        super(nombre, descripcion, duracion, video, rep, series, alFallo, Musculo.BRAZOS, descanso);
    }

    @Override public void mostrarVideo() { video.verVideo(); }
    @Override public void realizar() {
        System.out.println("Realizando ejercicio de BRAZOS: " + nombreEjercicio);
    }
    @Override public void mostrarTiempo() {
        System.out.println("Duración: " + duracion + " segundos");
    }
}
