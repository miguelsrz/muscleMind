package musclemind.app;

import java.time.LocalDateTime;
import musclemind.usuario.*;
import musclemind.rutina.*;
import musclemind.media.*;
import musclemind.ejercicio.*;

import java.util.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static Usuario usuarioActual = null;

    public static void main(String[] args) {
        while (true) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            System.out.println("");
            switch (opcion) {
                case 1 ->
                    registrarUsuario();
                case 2 ->
                    iniciarSesion();
                case 3 -> {
                    System.out.println("Gracias por utilizar MuscleMind.");
                    return;
                }
                default ->
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("");
        System.out.println("=== MUSCLEMIND ===");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar Sesión");
        System.out.println("3. Salir");

        System.out.println("");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private static void registrarUsuario() {
        System.out.println("--- MENU REGISTRO ---");
        System.out.print("Nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("PIN (4 a 6 numeros ) de contraseña: ");
        int password = Integer.parseInt(scanner.nextLine());
        System.out.print("Edad: ");
        int edad = Integer.parseInt(scanner.nextLine());
        System.out.print("Peso (kg): ");
        int peso = Integer.parseInt(scanner.nextLine());
        System.out.print("Objetivo (Hipertrofia, definicion, resistencia, recuperacion): ");
        String objetivo = scanner.nextLine();

        Usuario nuevo = new Usuario(nombre, password, edad, peso, objetivo, Rutina.generarRutinaPorObjetivo(objetivo));
        usuarios.add(nuevo);
        System.out.println("");
        System.out.println("Usuario registrado con éxito.");
    }

    private static void iniciarSesion() {
        System.out.println("--- MENU INICIO ---");
        System.out.print("Ingrese su nombre de usuario: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese su contraseña (número): ");
        int passIngresada;
        try {
            passIngresada = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("La contraseña debe ser un número.");
            return;
        }
        System.out.println();

        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equalsIgnoreCase(nombre)) {
                if (u.getPassword() == passIngresada) {
                    usuarioActual = u;
                    System.out.println("Sesión iniciada como: " + nombre + "\n");
                    menuUsuario();
                    return;
                } else {
                    System.out.println("Contraseña incorrecta.\n");
                    return;
                }
            }
        }
        System.out.println("Usuario no encontrado.\n");
    }

    private static void menuUsuario() {
        int opcion;
        do {
            System.out.println("--- MENU USUARIO ---");
            System.out.println("Hola, " + usuarioActual.getNombreUsuario());
            System.out.println("");
            System.out.println("1. Ver Rutinas");
            System.out.println("2. Crear Rutina");
            System.out.println("3. Registrar Sesión de Entrenamiento");
            System.out.println("4. Ver Progreso");
            System.out.println("5. Cerrar Sesión");
            System.out.println("");
            System.out.print("Seleccione una opción: ");
            opcion = leerOpcion();

            System.out.println("");
            switch (opcion) {
                case 1 ->
                    mostrarRutinas();
                case 2 ->
                    crearRutina();
                case 3 ->
                    iniciarEntrenamiento();
                case 4 ->
                    verProgreso();
                case 5 ->
                    System.out.println("Sesión cerrada.");
                default ->
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private static void mostrarRutinas() {
        System.out.println("--- MENU VER RUTINAS ---");
        ArrayList<Rutina> rutinas = usuarioActual.getListaRutinas();
        if (rutinas.isEmpty()) {
            System.out.println("No hay rutinas registradas.");
            return;
        }
        for (int i = 0; i < rutinas.size(); i++) {
            System.out.println("");
            System.out.println("#" + (i + 1));
            rutinas.get(i).verRutina();
        }
    }

    private static void crearRutina() {
        System.out.println("--- MENU CREAR RUTINA ---");
        System.out.print("Nombre de la nueva rutina: ");
        String nombre = scanner.nextLine();
        Rutina nueva = new Rutina(nombre);

        boolean agregar = true;
        while (agregar) {
            System.out.println("");
            System.out.println("1. Agregar ejercicio fuerza \n2. Agregar ejercicio cardio \n3. Finalizar");
            System.out.println("");
            System.out.print("Seleccione una opción: ");

            int opc = leerOpcion();
            switch (opc) {
                case 1 -> {
                    EjerciciosFuerza fuerza = crearEjercicioFuerza();
                    nueva.agregarEjercicio(fuerza);
                }
                case 2 -> {
                    EjerciciosCardio cardio = crearEjercicioCardio();
                    nueva.agregarEjercicio(cardio);
                }
                case 3 ->
                    agregar = false;
                default ->
                    System.out.println("Opción inválida.");
            }
        }
        usuarioActual.agregarRutina(nueva);
        System.out.println("Rutina creada.");
    }

    private static EjerciciosFuerza crearEjercicioFuerza() {
        System.out.print("Nombre del ejercicio: ");
        String nombre = scanner.nextLine();

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Duración (seg): ");
        int duracion = Integer.parseInt(scanner.nextLine());

        System.out.print("Repeticiones: ");
        int rep = Integer.parseInt(scanner.nextLine());

        System.out.print("Series: ");
        int series = Integer.parseInt(scanner.nextLine());

        System.out.print("¿Al fallo? (Si/No): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        boolean alFallo = respuesta.equals("si");

        System.out.print("Músculo (Pecho, Espalda, Brazo, Pierna, Core): ");
        String musculoStr = scanner.nextLine().trim().toLowerCase();

        System.out.print("Descanso (seg): ");
        int descanso = Integer.parseInt(scanner.nextLine());

        Video video = new Video("http://video.com/ejemplo", "Video ejemplo");

        return switch (musculoStr) {
            case "pecho" ->
                new EjerciciosPecho(nombre, descripcion, duracion, video, rep, series, alFallo, descanso);
            case "espalda" ->
                new EjerciciosEspalda(nombre, descripcion, duracion, video, rep, series, alFallo, descanso);
            case "brazo" ->
                new EjerciciosBrazo(nombre, descripcion, duracion, video, rep, series, alFallo, descanso);
            case "pierna" ->
                new EjerciciosPierna(nombre, descripcion, duracion, video, rep, series, alFallo, descanso);
            default ->
                new EjerciciosCore(nombre, descripcion, duracion, video, rep, series, alFallo, descanso) {
                };
        };
    }

    private static EjerciciosCardio crearEjercicioCardio() {
        System.out.print("Nombre del ejercicio: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Duración (seg): ");
        int duracion = Integer.parseInt(scanner.nextLine());
        System.out.print("Calorías estimadas: ");
        int calorias = Integer.parseInt(scanner.nextLine());
        System.out.print("Pulsaciones promedio: ");
        int pulsaciones = Integer.parseInt(scanner.nextLine());

        Video video = new Video("http://video.com/cardio", "Video cardio");
        return new EjerciciosCardio(nombre, descripcion, duracion, video, calorias, pulsaciones);
    }

    private static void iniciarEntrenamiento() {
        System.out.println("--- MENU INICIO ENTRENAMIENTO ---");
        ArrayList<Rutina> rutinas = usuarioActual.getListaRutinas();

        System.out.println("");
        if (rutinas.isEmpty()) {
            System.out.println("No tiene rutinas asignadas.");
            return;
        }

        for (int i = 0; i < rutinas.size(); i++) {
            System.out.println((i + 1) + ". " + rutinas.get(i).getNombreRutina());
        }
        System.out.println("");
        System.out.print("Seleccione una rutina: ");
        int opcion = Integer.parseInt(scanner.nextLine()) - 1;
        if (opcion < 0 || opcion >= rutinas.size()) {
            System.out.println("");
            System.out.println("Opción inválida.");
            System.out.println("");
            return;
        }

        Rutina rutinaSeleccionada = rutinas.get(opcion);
        ArrayList<Ejercicio> ejercicios = new ArrayList<>(rutinaSeleccionada.getListaEjercicios());
        ArrayList<String> resultados = new ArrayList<>();
        int caloriasTotales = 0;

        System.out.println("");
        System.out.println("Iniciando sesión de entrenamiento...");

        for (Ejercicio e : ejercicios) {
            System.out.println("");
            System.out.println("Ejercicio: " + e.getNombreEjercicio());

            if (e instanceof EjerciciosFuerza fuerza) {
                String resultado = "";

                for (int i = 1; i <= fuerza.getCantidadSeries(); i++) {
                    System.out.print("Serie " + i + " - Peso (kg): ");
                    int peso = Integer.parseInt(scanner.nextLine());
                    System.out.print("Serie " + i + " - Reps: ");
                    int reps = Integer.parseInt(scanner.nextLine());
                    resultado += "[" + peso + "kg x " + reps + " reps] ";
                }

                resultados.add(resultado.trim());
            } else if (e instanceof EjerciciosCardio cardio) {
                System.out.print("Duración real (segundos): ");
                int tiempo = Integer.parseInt(scanner.nextLine());
                System.out.print("Intensidad (frecuencia promedio bpm): ");
                int intensidad = Integer.parseInt(scanner.nextLine());

                resultados.add(tiempo + "s a " + intensidad + " bpm");
                caloriasTotales += cardio.getCaloriasQuemadas(); // puedes mejorar esto
            }
        }

        System.out.println("");
        System.out.print("Duración total (minutos): ");
        int duracion = Integer.parseInt(scanner.nextLine());

        Sesion sesion = new Sesion(rutinaSeleccionada.getNombreRutina(), LocalDateTime.now(), duracion, caloriasTotales, ejercicios);
        for (String r : resultados) {
            sesion.agregarResultado(r);
        }

        usuarioActual.agregarSesion(sesion);

        System.out.println("");
        System.out.println("Sesión registrada exitosamente.");
        System.out.println("");

    }

    public static void verProgreso() {

        ArrayList<Sesion> sesiones = usuarioActual.getHistorialSesiones();
        if (sesiones.isEmpty()) {
            System.out.println("No hay sesiones registradas aún.");
            return;
        }

        System.out.println("\n=== HISTORIAL DE PROGRESO ===\n");
        
        for (Sesion s : sesiones) {
            
            System.out.println("- Rutina: " + s.getNombreRutina());
            System.out.println("- Fecha: " + s.getFecha());
            System.out.println("- Duración: " + s.getDuracionReal() + " min");
            System.out.println("- Calorías estimadas: " + s.getCaloriasTotales());
            System.out.println("- Ejercicios:");

            ArrayList<Ejercicio> ejercicios = s.getEjerciciosRealizados();
            ArrayList<String> resultados = s.getResultadosEjercicios();

            for (int i = 0; i < ejercicios.size(); i++) {
                Ejercicio e = ejercicios.get(i);
                String resultado = resultados.get(i);

                if (e instanceof EjerciciosFuerza) {
                    String[] series = resultado.split("]");
                    int maxPeso = 0, repsMax = 0;

                    for (String serie : series) {
                        try {
                            String[] partes = serie.replace("[", "").trim().split("x");
                            int peso = Integer.parseInt(partes[0].replace("kg", "").trim());
                            int reps = Integer.parseInt(partes[1].replace("reps", "").trim());
                            if (peso > maxPeso || (peso == maxPeso && reps > repsMax)) {
                                maxPeso = peso;
                                repsMax = reps;
                            }
                        } catch (Exception ex) {
                            // en caso de error, se omite esa serie
                        }
                    }

                    System.out.println("   - " + e.getNombreEjercicio() + " | Peso máx: " + maxPeso + "kg x " + repsMax + " reps");
                } else if (e instanceof EjerciciosCardio) {
                    System.out.println("   - " + e.getNombreEjercicio() + " | " + resultado);
                }
            }
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("");
        }
    }

}
