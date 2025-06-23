
package musclemind.ejercicio;

import musclemind.media.Video;

public class EjerciciosEstiramiento extends Ejercicio {
    private Musculo musculoEstirado;

    public EjerciciosEstiramiento(String nombre, String descripcion, int duracion, Video video, Musculo musculo) {
        super(nombre, descripcion, duracion, video);
        this.musculoEstirado = musculo;
    }

    public Musculo getMusculoEstirado() { return musculoEstirado; }

    @Override public void mostrarVideo() { video.verVideo(); }
    @Override public void realizar() {
        System.out.println("Realizando estiramiento de: " + musculoEstirado);
    }
    @Override public void mostrarTiempo() {
        System.out.println("Duración del estiramiento: " + duracion + " segundos.");
    }
}
