package musclemind.ejercicio;


public class EjerciciosPecho extends EjerciciosFuerza {

    public EjerciciosPecho(String nombre, String descripcion, int duracion,
            int rep, int series, boolean alFallo, int descanso) {
        super(nombre, descripcion, duracion, rep, series, alFallo, Musculo.PECHO, descanso);
    }

}
