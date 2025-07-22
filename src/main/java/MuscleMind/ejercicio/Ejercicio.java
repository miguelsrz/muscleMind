// Clase abstracta Ejercicio
package musclemind.ejercicio;


public abstract class Ejercicio {
    protected String nombreEjercicio;
    protected String descripcionEjercicio;
    protected int duracion; // En segundos

    public Ejercicio(String nombre, String descripcion, int duracion) {
        this.nombreEjercicio = nombre;
        this.descripcionEjercicio = descripcion;
        this.duracion = duracion;
    }
    
    // Getters
    public String getNombreEjercicio() { return nombreEjercicio; }
    public String getDescripcionEjercicio() { return descripcionEjercicio; }
    public int getDuracion() { return duracion; }    
}
