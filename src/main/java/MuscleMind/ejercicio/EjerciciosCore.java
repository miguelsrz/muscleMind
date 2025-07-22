
package musclemind.ejercicio;


public class EjerciciosCore extends EjerciciosFuerza {
    public EjerciciosCore(String nombre, String descripcion, int duracion,
                          int rep, int series, boolean alFallo, int descanso) {
        super(nombre, descripcion, duracion, rep, series, alFallo, Musculo.GENERAL, descanso);
    }
}
