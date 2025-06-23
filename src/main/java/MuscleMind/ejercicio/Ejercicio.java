// Clase abstracta Ejercicio
package musclemind.ejercicio;

import musclemind.media.Video;

public abstract class Ejercicio {
    protected String nombreEjercicio;
    protected String descripcionEjercicio;
    protected int duracion; // En segundos, en un futuro cuando se vea interfaz se espera utilizar para calculos de calorias y relacionados
    protected Video video; // El usuario agrega link de un video para futuro uso que requiera, sino, sera uno por defecto, se usara cuando se vea interfaz

    public Ejercicio(String nombre, String descripcion, int duracion, Video video) {
        this.nombreEjercicio = nombre;
        this.descripcionEjercicio = descripcion;
        this.duracion = duracion;
        this.video = video;
    }
    
    // Getters
    public String getNombreEjercicio() { return nombreEjercicio; }
    public String getDescripcionEjercicio() { return descripcionEjercicio; }
    public int getDuracion() { return duracion; }
    public Video getVideo() { return video; }

    // Metodos posibles a implementar en futuro cuando se vea interfaz
    public abstract void mostrarVideo();
    public abstract void realizar();
    public abstract void mostrarTiempo();
}
