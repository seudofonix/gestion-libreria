package biblioteca.logica;

import java.time.LocalDate;
import java.util.Iterator;

import net.datastructures.ProbeHashMap;
import net.datastructures.Entry;
import net.datastructures.LinkedPositionalList;
import net.datastructures.LinkedQueue;
import biblioteca.modelo.Libro;
import biblioteca.modelo.Socio;
import biblioteca.modelo.Prestamo;

public class Logica {

    private ProbeHashMap<String, Libro> catalogo;
    private ProbeHashMap<String, Socio> socios;
    private ProbeHashMap<String, LinkedPositionalList<Prestamo>> prestamosActivos;
    private ProbeHashMap<String, LinkedPositionalList<Prestamo>> historialPrestamos;
    private ProbeHashMap<String, LinkedPositionalList<Socio>> listaEspera;
    
    // TODO: definir las estructuras adicionales que necesite
    // Pensar: ¿dónde guardar los préstamos activos?
    // Pensar: ¿cómo modelar la lista de espera por libro?
    // Pensar: ¿dónde guardar el historial de préstamos por socio?

    public Logica(ProbeHashMap<String, Libro> catalogo,
                  ProbeHashMap<String, Socio> socios,
                  ProbeHashMap<String, LinkedPositionalList<Prestamo>> prestamosActivos)
    {
    	
        this.catalogo = catalogo;
        this.socios   = socios;
        this.prestamosActivos = prestamosActivos;
        
        this.historialPrestamos = new ProbeHashMap<String, LinkedPositionalList<Prestamo>>();
        this.listaEspera = new ProbeHashMap<String, LinkedPositionalList<Socio>>();
        
    }

    // ── INCREMENTO 1 ──────────────────────────────────────────────

    /**
     * Registra el préstamo de un libro a un socio.
     * La fecha de préstamo es la fecha actual y el vencimiento se calcula
     * automáticamente (14 días).
     * Condiciones: el socio debe estar activo y debe haber ejemplares disponibles.
     * @return true si el préstamo se realizó, false en caso contrario
     */
    public boolean prestar(String nroSocio, String isbn)
    {    
	    	Libro libro = catalogo.get(isbn);
	    	Socio socio = socios.get(nroSocio);
	    	
	    	if ( libro.getEjemplaresDisponibles() < 1 || !socio.isActivo() )
	        return false;
	    	
	    	LocalDate hoy = LocalDate.now();
	    	LocalDate vencimiento = hoy.plusDays(14);
	    	Prestamo prestamo = new Prestamo(socio, libro, hoy, vencimiento);
	    	
	    	// Anadir el prestamo a la lista del socio correspondiente.
	    	if (prestamosActivos.get(socio.getNroSocio()) == null)
	    	prestamosActivos.put(socio.getNroSocio(), new LinkedPositionalList<Prestamo>());
	    	
	    	prestamosActivos.get(socio.getNroSocio()).addLast(prestamo);
	    	
	    	return true;
    }

    /**
     * Registra la devolución de un libro.
     * Actualiza el estado del préstamo y la disponibilidad del libro.
     * @return true si la devolución se realizó, false en caso contrario
     */
    public boolean devolver(String nroSocio, String isbn) {
	        
	        LinkedPositionalList<Prestamo> prestamos_socio = prestamosActivos.get(nroSocio);
	        if (prestamos_socio == null)  return false;  // Socio no tiene prestamos.
	        
	        // Revisa si el libro existe en la lista de prestamos.
	        Iterator<Prestamo> prestamo = prestamos_socio.iterator();
	        while(prestamo.hasNext())
	        {
	        		Prestamo actual = prestamo.next();
	        		if( isbn == actual.getLibro().getIsbn() )
	        		{
		        			prestamo.remove();  // Devolucion hecha
		        			return true;
	        		}
	        }
	        return false;  // No se encontro un prestamo para el libro especificado.
    }
    
    /**
     * Busca un libro por su ISBN.
     * @return el Libro encontrado, o null si no existe
     */
    public Libro buscarPorIsbn(String isbn) {
    		return catalogo.get(isbn);
    }

    /**
     * Busca libros cuyo título contenga la cadena indicada (sin distinguir mayúsculas).
     * @return Lista de coincidencias. De no haber retorna lista vacia.
     */
    public LinkedPositionalList<Libro> buscarPorTitulo(String titulo) {
    	
    	/* Como el hashMap esta condicionado por ISBN, no nos queda de otra que iterar el mapa entero */
    	LinkedPositionalList<Libro> coincidencias = new LinkedPositionalList<Libro>();
    	
    	// Crea un iterador a partir del hashmap catalogo. (ayudame torvalds)
        for(Entry<String,Libro> entradaActual : catalogo.entrySet())
        {
	        	Libro libro = entradaActual.getValue();
	        	if (libro.getTitulo().toLowerCase() == titulo.toLowerCase())  coincidencias.addLast(libro);
        }
        
        return coincidencias;  // Si no hay coincidencias, la lista esta vacia.
    }
    
    /**
     * Busca libros de un autor dado (sin distinguir mayúsculas).
     * @return Lista de coincidencias. De no haber retorna lista vacia.
     */
    public LinkedPositionalList<Libro> buscarPorAutor(String autor) {
    	
    	/* Como el hashMap esta condicionado por ISBN, no nos queda de otra que iterar el mapa entero */
    	LinkedPositionalList<Libro> coincidencias = new LinkedPositionalList<Libro>();
    	
    	// Crea un iterador a partir del hashmap catalogo.
        for(Entry<String,Libro> entradaActual : catalogo.entrySet())
        {
	        	Libro libro = entradaActual.getValue();
	        	if (libro.getAutor().toLowerCase() == autor.toLowerCase())  coincidencias.addLast(libro);
        }
        
        return coincidencias;  // Si no hay coincidencias, la lista esta vacia.
    }

    /**
     * Retorna todos los libros con al menos un ejemplar disponible.
     * @return Lista de libros disponibles. De no haber retorna lista vacia.
     */
    public LinkedPositionalList<Libro> listarDisponibles()
    {
	        LinkedPositionalList<Libro> disponibles = new LinkedPositionalList<Libro>();
	        
	        for(Entry<String,Libro> entradaActual : catalogo.entrySet())
	        {
	        	Libro libro = entradaActual.getValue();
	        	if ( libro.getEjemplaresDisponibles() >= 1 )  disponibles.addLast(libro);
	        }
	        
	        return disponibles;
    }

    /**
     * Retorna los préstamos activos de un socio.
     * @return Lista de coincidencias. De no haber retorna lista vacia.
     */
    public LinkedPositionalList<Prestamo> prestamosActivosDeSocio(String nroSocio) {
        return prestamosActivos.get(nroSocio);
    }

    // ── INCREMENTO 2 ──────────────────────────────────────────────

    /**
     * Agrega un socio a la cola de espera de un libro.
     * Se invoca cuando no hay ejemplares disponibles al momento del pedido.
     */
    public void agregarEspera(String nroSocio, String isbn) {
        
    }

    /**
     * Al devolver un libro, si hay socios en espera, asigna el ejemplar
     * automáticamente al primero en la cola y lo notifica.
     */
    public void asignarSiguienteEnEspera(String isbn) {
        // TODO: implementar
    }

    /**
     * Retorna el historial completo de préstamos de un socio
     * (activos e históricos), en orden cronológico.
     */
    public LinkedPositionalList<Prestamo> historialDeSocio(String nroSocio) {
        // TODO: implementar
        return null;
    }

    /**
     * Retorna los N libros más solicitados (préstamos activos + históricos).
     * @param n cantidad de libros a retornar
     */
    public LinkedPositionalList<Libro> librosMasSolicitados(int n) {
        // TODO: implementar
        return null;
    }

    /**
     * Retorna todos los préstamos cuya fecha de vencimiento expiró
     * y que aún no fueron devueltos.
     * @param hoy fecha actual
     */
    public LinkedPositionalList<Prestamo> prestamosVencidos(LocalDate hoy) {
        // TODO: implementar
        return null;
    }
}
