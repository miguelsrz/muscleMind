//  Logica fue pasada a Rutina

//package musclemind.usuario;
//
//import musclemind.ejercicio.Ejercicio;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.time.format.DateTimeFormatter;
//
//public class Sesion {
//
//    private String nombreRutina;
//    private LocalDateTime fecha;
//    private int duracionReal;
//    private int caloriasTotales;
//    private ArrayList<Ejercicio> ejerciciosRealizados;
//    private ArrayList<String> resultadosEjercicios; // detalles por ejercicio
//
//    public Sesion(String nombreRutina, LocalDateTime fecha, int duracionReal, int caloriasTotales, ArrayList<Ejercicio> ejerciciosRealizados) {
//        this.nombreRutina = nombreRutina;
//        this.fecha = fecha;
//        this.duracionReal = duracionReal;
//        this.caloriasTotales = caloriasTotales;
//        this.ejerciciosRealizados = ejerciciosRealizados;
//        this.resultadosEjercicios = new ArrayList<>();
//    }
//
//    public String getNombreRutina() {
//        return nombreRutina;
//    }
//
//    public String getFecha() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy, HH:mm");
//        String fechaLegible = fecha.format(formatter);
//        return fechaLegible;
//    }
//
//    public int getDuracionReal() {
//        return duracionReal;
//    }
//
//    public int getCaloriasTotales() {
//        return caloriasTotales;
//    }
//
//    public ArrayList<Ejercicio> getEjerciciosRealizados() {
//        return ejerciciosRealizados;
//    }
//
//    public ArrayList<String> getResultadosEjercicios() {
//        return resultadosEjercicios;
//    }
//
//    public void agregarResultado(String resultado) {
//        resultadosEjercicios.add(resultado);
//    }
//
//    public void mostrarResumen() {
//        System.out.println("Resumen de sesión: " + nombreRutina);
//        System.out.println("Fecha: " + fecha);
//        System.out.println("Duración: " + duracionReal + " minutos");
//        System.out.println("Calorías quemadas: " + caloriasTotales);
//        System.out.println("Ejercicios realizados: " + ejerciciosRealizados.size());
//        for (int i = 0; i < ejerciciosRealizados.size(); i++) {
//            System.out.println("→ " + ejerciciosRealizados.get(i).getNombreEjercicio() + " | Resultado: " + resultadosEjercicios.get(i));
//        }
//    }
//}
