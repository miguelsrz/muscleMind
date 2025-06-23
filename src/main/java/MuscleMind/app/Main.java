package musclemind.app;

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
        System.out.print("PIN (4 a 6 numeros )de contraseña: ");
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
        System.out.println("--- MENU INICIO  ---");
        System.out.print("Ingrese su nombre de usuario: ");
        String nombre = scanner.nextLine();
        System.out.println("");

        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equalsIgnoreCase(nombre)) {
                usuarioActual = u;
                System.out.println("Sesión iniciada como: " + nombre);
                System.out.println("");
                menuUsuario();
                return;
            }
        }
        System.out.println("Usuario no encontrado.");
    }

    private static void menuUsuario() {
        int opcion;
        do {
            System.out.println("--- MENU USUARIO ---");
            System.out.println("1. Ver Rutinas");
            System.out.println("2. Crear Rutina");
            System.out.println("3. Registrar Sesión de Entrenamiento");
            System.out.println("4. Ver Progreso");
            System.out.println("5. Cerrar Sesión");
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
                    System.out.println("Funcionalidad en desarrollo.");
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
        System.out.print("Músculo (Pecho, Espalda, Brazo, Pierna, Core, General): ");
        Musculo musculo = Musculo.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Descanso (seg): ");
        int descanso = Integer.parseInt(scanner.nextLine());

        Video video = new Video("http://video.com/ejemplo", "Video ejemplo");
        return new EjerciciosFuerza(nombre, descripcion, duracion, video, rep, series, alFallo, musculo, descanso) {
            @Override
            public void mostrarVideo() {
                video.verVideo();
            }

            @Override
            public void realizar() {
                System.out.println("Realizando fuerza: " + nombreEjercicio);
            }

            @Override
            public void mostrarTiempo() {
                System.out.println("Tiempo: " + duracion + " s");
            }
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
        if (rutinas.isEmpty()) {
            System.out.println("No hay rutinas disponibles.");
            return;
        }
        mostrarRutinas();
        System.out.print("Seleccione número de rutina: ");
        int index = leerOpcion() - 1;
        if (index >= 0 && index < rutinas.size()) {
            Rutina rutina = rutinas.get(index);
            usuarioActual.empezarRutina(rutina.getNombreRutina());
            rutina.verRutina();
            usuarioActual.terminarRutina(rutina.getNombreRutina());
        } else {
            System.out.println("Selección inválida.");
        }
    }
}
