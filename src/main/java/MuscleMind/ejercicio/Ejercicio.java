
package musclemind.ejercicio;

import musclemind.media.Video;

public abstract class Ejercicio {
    protected String nombreEjercicio;
    protected String descripcionEjercicio;
    protected int duracion; // en segundos
    protected Video video;

    public Ejercicio(String nombre, String descripcion, int duracion, Video video) {
        this.nombreEjercicio = nombre;
        this.descripcionEjercicio = descripcion;
        this.duracion = duracion;
        this.video = video;
    }

    public String getNombreEjercicio() { return nombreEjercicio; }
    public String getDescripcionEjercicio() { return descripcionEjercicio; }
    public int getDuracion() { return duracion; }
    public Video getVideo() { return video; }

    public abstract void mostrarVideo();
    public abstract void realizar();
    public abstract void mostrarTiempo();
}
