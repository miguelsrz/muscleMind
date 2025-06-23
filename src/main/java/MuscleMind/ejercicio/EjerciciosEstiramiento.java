// La clase no ha sido implementada en la aplicacion de consola. Se espera utilziar cuando se vea interfaz
// Permitira diferenciar el tipo de ejercicio, y sera una clase de guia para el usuario para que realice el estiramiento como se debe por el tiempo que corresponda
package musclemind.ejercicio;

import musclemind.media.Video;

public class EjerciciosEstiramiento extends Ejercicio {
    private Musculo musculoEstirado;

    public EjerciciosEstiramiento(String nombre, String descripcion, int duracion, Video video, Musculo musculo) {
        super(nombre, descripcion, duracion, video);
        this.musculoEstirado = musculo;
    }
    
    // Getters
    public Musculo getMusculoEstirado() { return musculoEstirado; }
    
    // Se espera utilizar cuando se implementen con interfaz
    @Override public void mostrarVideo() { video.verVideo(); }
    @Override public void realizar() {
        System.out.println("Realizando estiramiento de: " + musculoEstirado);
    }
    @Override public void mostrarTiempo() {
        System.out.println("Duración del estiramiento: " + duracion + " segundos.");
    }
}
