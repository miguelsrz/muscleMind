
package musclemind.ejercicio;


public class EjerciciosBrazo extends EjerciciosFuerza {
    public EjerciciosBrazo(String nombre, String descripcion, int duracion,
                          int rep, int series, boolean alFallo, int descanso) {
        super(nombre, descripcion, duracion, rep, series, alFallo, Musculo.BRAZOS, descanso);
    }

}
