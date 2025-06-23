// Aplicacion de consola que hace demostracion del flujo esperado de un usuario al implementar interfaz grafica
// Se utilizan mayoria de clases y sus metodos, sin embargo, se espera ampliar en funcionalidades y la calidad de ellas cuando se implemente interfaz grafics
package musclemind.app;

// Imports necesarios
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

    // Metodo main, donde a partir de aqui se orienta hacia el flujo del usuario
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

    // Menu para registro, inicio sesion y salida de aplicacion
    private static void mostrarMenuPrincipal() {
        System.out.println("");
        System.out.println("=== MUSCLEMIND ===");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar Sesión");
        System.out.println("3. Salir");

        System.out.println("");
        System.out.print("Seleccione una opción: ");
    }

    // Metodo para reducir codigo, usos internos
    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    // Registro de un usuario simulado basico
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

    // Inicio de sesion de un usuario simulado basico
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

    // Cuando se ingresa sesion, el usuario puede acceder a las distintas funcionalidas basicas de la aplicacion desarrolladas para la demostracion
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

    // Permite visualizar las rutinas y ejercicios del usuario
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

    // Se crea una rutina con ejercicios de fuerza o cardio
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

    // Metodo para reduccion de codigo
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

    // Metodo para reduccion de codigo
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

    // Se inicia el registro de datos de un entrenamiento. Los datos de ejercicio dados cuando se agrego a una rutina son de referencia para el usuario en un futuro, mas en la practica el usuario puede ingresar los datos que completo mientras realizaba la rutina
    private static void iniciarEntrenamiento() {
        System.out.println("--- MENU INICIO ENTRENAMIENTO ---");
        ArrayList<Rutina> rutinas = usuarioActual.getListaRutinas();

        // En dado caso
        System.out.println();
        if (rutinas.isEmpty()) {
            System.out.println("No tiene rutinas asignadas.");
            return;
        }

        // Se escoje la rutina a realizar
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
        ArrayList<Ejercicio> ejercicios = rutinaSeleccionada.getListaEjercicios();
        ArrayList<String> resultados = new ArrayList<>();

        // Los calculos de calorias son temporales, se mejoraran cuando se implemente interfaz
        int caloriasTotales = 0;

        System.out.println("");
        System.out.println("Iniciando sesión de entrenamiento...");

        // Por cada tipo de ejercicio se registran los datos de interes
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
                    caloriasTotales += 20;
                }
                resultados.add(resultado.trim());
            } else if (e instanceof EjerciciosCardio cardio) {
                System.out.print("Duración real (segundos): ");
                int tiempo = Integer.parseInt(scanner.nextLine());
                System.out.print("Intensidad (frecuencia promedio bpm): ");
                int intensidad = Integer.parseInt(scanner.nextLine());

                resultados.add(tiempo + "s a " + intensidad + " bpm");
                caloriasTotales += cardio.getCaloriasQuemadas();
            } else {
                resultados.add("Ejercicio no evaluado.");
            }
        }

        System.out.println();
        System.out.print("Duración total (minutos): ");
        int duracion = Integer.parseInt(scanner.nextLine());

        // Se registra la sesion directamente en la rutina asociada
        rutinaSeleccionada.registrarSesion(duracion, caloriasTotales, resultados);

        System.out.println("\nSesión registrada exitosamente.\n");
    }

    public static void verProgreso() {
        ArrayList<Rutina> rutinas = usuarioActual.getListaRutinas();

        System.out.println("--- MENU VER PROGRESO ---");

        System.out.println("");
        if (rutinas.isEmpty()) {
            System.out.println("No tiene rutinas asignadas.");
            return;
        }
        
        // Se selecciona rutina para ver su historico
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
        
        // Utilizando el metodo de la rutina, se ve los datos historicos
        System.out.println("\n=== HISTORIAL DE PROGRESO ===\n");
        rutinaSeleccionada.verProgreso();

    }

}
