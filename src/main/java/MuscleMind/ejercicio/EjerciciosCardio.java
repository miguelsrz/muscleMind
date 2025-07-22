package musclemind.ejercicio;


public class EjerciciosCardio extends Ejercicio {
    private int caloriasQuemadas; 
    private int pulsacionesPromedio;

    public EjerciciosCardio(String nombre, String descripcion, int duracion,
                            int calorias, int pulsaciones) {
        super(nombre, descripcion, duracion);
        this.caloriasQuemadas = calorias;
        this.pulsacionesPromedio = pulsaciones;
    }

    // Getters
    public int getCaloriasQuemadas() { return caloriasQuemadas; }
    public int getPulsacionesPromedio() { return pulsacionesPromedio; }
    
    // Se esperan utilizar cuando se vean interfaz
   
}