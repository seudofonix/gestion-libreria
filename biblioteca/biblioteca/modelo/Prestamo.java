package biblioteca.modelo;

import java.time.LocalDate;

public class Prestamo {

    private Socio     socio;
    private Libro     libro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento;
    private boolean   activo = true;

    public Prestamo(Socio socio, Libro libro,
                    LocalDate fechaPrestamo, LocalDate fechaVencimiento) {
        this.socio = socio;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaVencimiento = fechaVencimiento;
        // Nota: al crear un préstamo, activo debe ser true
    }

    /**
     * Retorna true si el préstamo está activo y la fecha de vencimiento
     * es anterior a la fecha indicada.
     */
    /**
     * @param hoy
     * @return si esta vencido
     */
    public boolean estaVencido(LocalDate hoy) {
        return fechaVencimiento.isBefore(hoy) && activo;
    }

	/**
	 * @return socio
	 */
	public Socio getSocio() {
		return socio;
	}

	/**
	 * @param socio
	 */
	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	/**
	 * @return libro
	 */
	public Libro getLibro() {
		return libro;
	}

	/**
	 * @param libro
	 */
	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	/**
	 * @return fecha prestamo
	 */
	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}

	/**
	 * @param fechaPrestamo
	 */
	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	/**
	 * @return fecha de vencimiento
	 */
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * @param fechaVencimiento
	 */
	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * @return si esta activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * @param activo
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "\nPrestamo: \nSocio= " + socio.getNombre() + libro + "\nFecha de prestamo= " + fechaPrestamo
				+ "\nFecha de vencimiento= " + fechaVencimiento + "\nActivo= " + activo;
	}

}
