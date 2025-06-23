// Se espera utilizar esta clase cuando se vea interfaz, pasando logica de Rutina aqui.
package musclemind.usuario;

import java.time.LocalDate;

public class ProgresoUsuario {
    private LocalDate fecha;
    private int pesoActual;
    private int repeticionesMaximas;
    private int distanciaRecorrida;

    public ProgresoUsuario(LocalDate fecha, int pesoActual, int repeticionesMaximas, int distanciaRecorrida) {
        this.fecha = fecha;
        this.pesoActual = pesoActual;
        this.repeticionesMaximas = repeticionesMaximas;
        this.distanciaRecorrida = distanciaRecorrida;
    }

    public void mostrarProgreso() {
        System.out.println("Fecha: " + fecha);
        System.out.println("Peso actual: " + pesoActual + " kg");
        System.out.println("Repeticiones máximas: " + repeticionesMaximas);
        System.out.println("Distancia recorrida: " + distanciaRecorrida + " metros");
    }
}
