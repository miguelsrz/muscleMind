package musclemind.ejercicio;


public class EjerciciosPierna extends EjerciciosFuerza {

    public EjerciciosPierna(String nombre, String descripcion, int duracion,
            int rep, int series, boolean alFallo, int descanso) {
        super(nombre, descripcion, duracion, rep, series, alFallo, Musculo.PIERNA, descanso);
    }

}
