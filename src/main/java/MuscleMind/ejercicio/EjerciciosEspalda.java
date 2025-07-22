package musclemind.ejercicio;


public class EjerciciosEspalda extends EjerciciosFuerza {

    public EjerciciosEspalda(String nombre, String descripcion, int duracion,
            int rep, int series, boolean alFallo, int descanso) {
        super(nombre, descripcion, duracion, rep, series, alFallo, Musculo.ESPALDA, descanso);
    }

}
