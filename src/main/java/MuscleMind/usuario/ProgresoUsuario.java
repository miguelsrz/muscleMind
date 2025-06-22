
package musclemind.usuario;

import java.time.LocalDate;

public class ProgresoUsuario {
    private LocalDate fecha;
    private int pesoActual;
    private int repeticionesMaximas;
    private int distanciaRecorrida;

    public ProgresoUsuario(LocalDate fecha, int peso, int repeticiones, int distancia) {
        this.fecha = fecha;
        this.pesoActual = peso;
        this.repeticionesMaximas = repeticiones;
        this.distanciaRecorrida = distancia;
    }
}
