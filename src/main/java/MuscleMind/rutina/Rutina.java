package musclemind.rutina;

import musclemind.ejercicio.*;
import musclemind.media.Video;
import java.util.ArrayList;

public class Rutina {

    private String nombreRutina;
    private ArrayList<Ejercicio> listaEjercicios;

    public Rutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
        this.listaEjercicios = new ArrayList<>();
    }

    public String getNombreRutina() {
        return nombreRutina;
    }

    public ArrayList<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    public void agregarEjercicio(Ejercicio e) {
        listaEjercicios.add(e);
    }

    public void verRutina() {
        System.out.println("Rutina: " + nombreRutina);
        System.out.println("Total ejercicios: " + listaEjercicios.size());
        for (Ejercicio e : listaEjercicios) {
            System.out.println(" - " + tipoEjercicioLegible(e) + ": " + e.getNombreEjercicio());
        }
        System.out.println("");
    }

    // Creador de rutinas predeterminadas
    private String tipoEjercicioLegible(Ejercicio e) {
        if (e instanceof EjerciciosPecho) {
            return "Pecho";
        }
        if (e instanceof EjerciciosEspalda) {
            return "Espalda";
        }
        if (e instanceof EjerciciosBrazo) {
            return "Brazos";
        }
        if (e instanceof EjerciciosPierna) {
            return "Piernas";
        }
        if (e instanceof EjerciciosCore) {
            return "Core";
        }
        if (e instanceof EjerciciosCardio) {
            return "Cardio";
        }
        if (e instanceof EjerciciosFuerza) {
            return "Fuerza";
        }
        return "General";
    }

    public static Rutina generarRutinaPorObjetivo(String objetivo) {
        objetivo = objetivo.toLowerCase();
        switch (objetivo) {
            case "hipertrofia":
                return crearHipertrofia();
            case "definicion":
                return crearDefinicion();
            case "resistencia":
                return crearResistencia();
            case "recuperacion":
                return crearRecuperacion();
            default:
                return new Rutina("Rutina Vacía");
        }
    }

    private static Rutina crearHipertrofia() {
        Rutina r = new Rutina("Fuerza - Hipertrofia");
        Video v = new Video("http://video.com", "Video base");

        r.agregarEjercicio(new EjerciciosPecho("Press banca", "Ejercicio compuesto de pecho", 60, v, 12, 4, false, 60));
        r.agregarEjercicio(new EjerciciosEspalda("Jalón al pecho", "Ejercicio dorsal", 60, v, 10, 4, false, 60));
        r.agregarEjercicio(new EjerciciosBrazo("Curl bíceps", "Aislado de brazo", 45, v, 15, 3, false, 45));
        r.agregarEjercicio(new EjerciciosPierna("Sentadillas", "Básico pierna", 60, v, 12, 4, false, 60));
        r.agregarEjercicio(new EjerciciosCore("Plancha", "Activación de core", 30, v, 1, 3, false, 30));

        return r;
    }

    private static Rutina crearDefinicion() {
        Rutina r = new Rutina("Pérdida de peso - Definición");
        Video v = new Video("http://video.com", "Video cardio");

        r.agregarEjercicio(new EjerciciosCardio("Burpees", "Cardio intenso", 60, v, 100, 140));
        r.agregarEjercicio(new EjerciciosPierna("Zancadas", "Zancadas con peso", 45, v, 15, 3, false, 30));
        r.agregarEjercicio(new EjerciciosCore("Crunch abdominal", "Básico de abdomen", 30, v, 20, 3, false, 30));
        r.agregarEjercicio(new EjerciciosCardio("Cuerda", "Saltos", 120, v, 80, 150));

        return r;
    }

    private static Rutina crearResistencia() {
        Rutina r = new Rutina("Resistencia cardiovascular");
        Video v = new Video("http://video.com", "Cardio base");

        r.agregarEjercicio(new EjerciciosCardio("Trote en sitio", "Cardio suave", 300, v, 200, 130));
        r.agregarEjercicio(new EjerciciosCardio("Jumping Jacks", "Explosivo", 60, v, 150, 145));
        r.agregarEjercicio(new EjerciciosCardio("Mountain climbers", "Estático intenso", 60, v, 120, 140));
        r.agregarEjercicio(new EjerciciosPierna("Sentadillas rápidas", "Ritmo alto", 60, v, 20, 3, false, 30));

        return r;
    }

    private static Rutina crearRecuperacion() {
        Rutina r = new Rutina("Recuperación y mantenimiento");
        Video v = new Video("http://video.com", "Estiramientos");

        r.agregarEjercicio(new EjerciciosCardio("Caminata", "Movimiento leve", 300, v, 80, 110));
        r.agregarEjercicio(new EjerciciosCore("Bird-Dog", "Estabilización", 45, v, 10, 3, false, 30));
        r.agregarEjercicio(new EjerciciosPierna("Elevaciones talón", "Piernas suaves", 30, v, 20, 3, false, 30));

        return r;
    }
}
