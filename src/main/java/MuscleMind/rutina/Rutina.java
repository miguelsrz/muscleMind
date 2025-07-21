package musclemind.rutina;

import musclemind.ejercicio.*;
import musclemind.media.Video;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Rutina {

    private String nombreRutina;
    private ArrayList<Ejercicio> listaEjercicios; // Ejercicios de la rutina, agregacion
    // Se ve la posibilidad de cambiar la relacion si requiere a composicion, depende implementacion cuando se vea interfaz

    // Atributos para tener registro de las sesiones realizadas
    private ArrayList<LocalDateTime> fechasSesiones;
    private ArrayList<Integer> duraciones;
    private ArrayList<Integer> calorias;
    private ArrayList<ArrayList<ArrayList<String>>> resultadosPorSesion;

    public Rutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
        this.listaEjercicios = new ArrayList<>();
        this.fechasSesiones = new ArrayList<>();
        this.duraciones = new ArrayList<>();
        this.calorias = new ArrayList<>();
        this.resultadosPorSesion = new ArrayList<>();
        // Todo lo necesario para administrar una rutina
    }

    public ArrayList<LocalDateTime> getFechasSesiones() {
        return fechasSesiones;
    }

    public ArrayList<ArrayList<ArrayList<String>>> getResultadosPorSesion() {
        return resultadosPorSesion;
    }

    public ArrayList<Integer> getDuraciones() {
        return duraciones;
    }

    public ArrayList<Integer> getCalorias() {
        return calorias;
    }

    // Getters
    public String getNombreRutina() {
        return nombreRutina;
    }

    public ArrayList<Ejercicio> getListaEjercicios() {
        return listaEjercicios;
    }

    // Se espera utilizar cuando se vea interfaz
    public void agregarEjercicio(Ejercicio e) {
        listaEjercicios.add(e);
    }

    // Muestra los ejercicios de una rutina con su tipo, nombre, descripción y detalles técnicos
    public void verRutina() {
        System.out.println("Rutina: " + nombreRutina);
        System.out.println("Total ejercicios: " + listaEjercicios.size());
        for (Ejercicio e : listaEjercicios) {
            System.out.print(" - " + tipoEjercicioLegible(e) + ": " + e.getNombreEjercicio());
            System.out.print(" / " + e.getDescripcionEjercicio());

            if (e instanceof EjerciciosFuerza f) {
                System.out.printf(" | %d reps x %d series", f.getCantidadRepeticiones(), f.getCantidadSeries());
                System.out.printf(" | %ds descanso", f.getTiempoDescanso());
                if (f.esAlFallo()) {
                    System.out.print(" | al fallo");
                }
            } else if (e instanceof EjerciciosCardio c) {
                System.out.printf(" | Intensidad estimada: %.1f km/h", c.getPulsacionesPromedio());
                System.out.printf(" | Duración: %.1f min", c.getDuracion());
            } else if (e instanceof EjerciciosEstiramiento s) {
                System.out.printf(" | Duración: %.1f min", s.getDuracion());
            }

            System.out.println();
        }
        System.out.println();
    }

    public String obtenerResumenEjercicios() {
        StringBuilder sb = new StringBuilder();
        for (Ejercicio e : listaEjercicios) {
            sb.append("• ").append(e.getNombreEjercicio()).append(": ").append(e.getDescripcionEjercicio());

            if (e instanceof EjerciciosFuerza f) {
                sb.append(String.format(" | %d reps x %d series", f.getCantidadRepeticiones(), f.getCantidadSeries()));
                sb.append(String.format(" | %ds descanso", f.getTiempoDescanso()));
                if (f.esAlFallo()) {
                    sb.append(" | al fallo");
                }
            } else if (e instanceof EjerciciosCardio c) {
                sb.append(String.format(" | %.1f min a %.1f km/h", c.getDuracion(), c.getPulsacionesPromedio()));
            } else if (e instanceof EjerciciosEstiramiento s) {
                sb.append(String.format(" | %.1f min estiramiento", s.getDuracion()));
            }

            sb.append("\n");
        }
        return sb.toString();
    }

    // Metodo para usos internos, permite ver el tipo de musculo que trabaja un ejercicio de fuerza
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

    // Registro de una sesión completa
    public void registrarSesion(int duracion, int caloriasQuemadas, ArrayList<ArrayList<String>> resultadosEjercicios) {
        fechasSesiones.add(LocalDateTime.now());
        duraciones.add(duracion);
        calorias.add(caloriasQuemadas);
        resultadosPorSesion.add(resultadosEjercicios);
    }

    public void verProgresoDetallado() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Progreso de la rutina: " + nombreRutina);
        for (int i = 0; i < fechasSesiones.size(); i++) {
            System.out.println("\n---------------- SESIÓN #" + (i + 1) + " ----------------");
            System.out.println("Fecha: " + fechasSesiones.get(i).format(formatter));
            System.out.println("Duración: " + duraciones.get(i) + " minutos");
            System.out.println("Calorías: " + calorias.get(i) + " kcal");
            System.out.println("Ejercicios realizados:");

            ArrayList<ArrayList<String>> resultadosEjercicios = resultadosPorSesion.get(i);

            for (int j = 0; j < resultadosEjercicios.size(); j++) {
                Ejercicio e = listaEjercicios.get(j);
                System.out.println("  ➤ " + e.getNombreEjercicio() + " - " + e.getDescripcionEjercicio());
                for (String resultadoSerie : resultadosEjercicios.get(j)) {
                    System.out.println("     ▪ " + resultadoSerie);
                }
            }
        }
        if (fechasSesiones.isEmpty()) {
            System.out.println("Aún no se han registrado sesiones.");
        }
    }

    // Crer rutinas predeterminadas dependiendo del objetivo
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
        Rutina r = new Rutina("(Predeterminada) Fuerza - Hipertrofia");
        Video v = new Video("http://video.com", "Video base");
        r.agregarEjercicio(new EjerciciosPecho("Press banca", "Ejercicio compuesto de pecho", 60, v, 12, 4, false, 60));
        r.agregarEjercicio(new EjerciciosEspalda("Jalón al pecho", "Ejercicio dorsal", 60, v, 10, 4, false, 60));
        r.agregarEjercicio(new EjerciciosBrazo("Curl bíceps", "Aislado de brazo", 45, v, 15, 3, false, 45));
        r.agregarEjercicio(new EjerciciosPierna("Sentadillas", "Básico pierna", 60, v, 12, 4, false, 60));
        r.agregarEjercicio(new EjerciciosCore("Plancha", "Activación de core", 30, v, 1, 3, false, 30));
        return r;
    }

    private static Rutina crearDefinicion() {
        Rutina r = new Rutina("(Predeterminada) Pérdida de peso - Definición");
        Video v = new Video("http://video.com", "Video cardio");
        r.agregarEjercicio(new EjerciciosCardio("Burpees", "Cardio intenso", 60, v, 100, 140));
        r.agregarEjercicio(new EjerciciosPierna("Zancadas", "Zancadas con peso", 45, v, 15, 3, false, 30));
        r.agregarEjercicio(new EjerciciosCore("Crunch abdominal", "Básico de abdomen", 30, v, 20, 3, false, 30));
        r.agregarEjercicio(new EjerciciosCardio("Cuerda", "Saltos", 120, v, 80, 150));
        return r;
    }

    private static Rutina crearResistencia() {
        Rutina r = new Rutina("(Predeterminada) Resistencia cardiovascular");
        Video v = new Video("http://video.com", "Cardio base");
        r.agregarEjercicio(new EjerciciosCardio("Trote en sitio", "Cardio suave", 300, v, 200, 130));
        r.agregarEjercicio(new EjerciciosCardio("Jumping Jacks", "Explosivo", 60, v, 150, 145));
        r.agregarEjercicio(new EjerciciosCardio("Mountain climbers", "Estático intenso", 60, v, 120, 140));
        r.agregarEjercicio(new EjerciciosPierna("Sentadillas rápidas", "Ritmo alto", 60, v, 20, 3, false, 30));
        return r;
    }

    private static Rutina crearRecuperacion() {
        Rutina r = new Rutina("(Predeterminada) Recuperación y mantenimiento");
        Video v = new Video("http://video.com", "Estiramientos");
        r.agregarEjercicio(new EjerciciosCardio("Caminata", "Movimiento leve", 300, v, 80, 110));
        r.agregarEjercicio(new EjerciciosCore("Bird-Dog", "Estabilización", 45, v, 10, 3, false, 30));
        r.agregarEjercicio(new EjerciciosPierna("Elevaciones talón", "Piernas suaves", 30, v, 20, 3, false, 30));
        return r;
    }

    @Override
    public String toString() {
        return nombreRutina;
    }

}
