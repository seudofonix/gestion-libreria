package biblioteca.modelo;

import java.time.LocalDate;

public class Prestamo {

    private Socio     socio;
    private Libro     libro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento;
    private boolean   activo;

    public Prestamo(Socio socio, Libro libro,
                    LocalDate fechaPrestamo, LocalDate fechaVencimiento) {
        // TODO: inicializar atributos
        // Nota: al crear un préstamo, activo debe ser true
    }

    // TODO: getters y setters

    /**
     * Retorna true si el préstamo está activo y la fecha de vencimiento
     * es anterior a la fecha indicada.
     */
    public boolean estaVencido(LocalDate hoy) {
        // TODO: implementar
        return false;
    }

    @Override
    public String toString() {
        // TODO: implementar
        return null;
    }
}
