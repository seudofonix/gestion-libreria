package biblioteca.datos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.time.LocalDate;

import net.datastructures.ProbeHashMap;
import net.datastructures.LinkedPositionalList;
import biblioteca.modelo.Libro;
import biblioteca.modelo.Socio;
import biblioteca.modelo.Prestamo;

public class Dato {

    /**
     * Carga los libros desde un archivo de texto.
     * Formato de cada línea: isbn;titulo;autor;genero;anio;ejemplares
     * Ejemplo: 978-0;Cien años de soledad;García Márquez;Novela;1967;3
     *
     * @return mapa indexado por ISBN
     */
    public static ProbeHashMap<String, Libro> cargarLibros(String fileName)
            throws FileNotFoundException {

        ProbeHashMap<String, Libro> libros = new ProbeHashMap<>();
        
        File archivo = new File(fileName);
        String linea = "";
        String isbn = "", titulo = "", autor = "", genero = "";
        int anio = 0, ejemplares = 0;
        try(Scanner scanner = new Scanner(archivo))
        {
        	while(scanner.hasNextLine())   // Leer linea por linea el archivo entero.
        	{
		        	linea = scanner.nextLine();
		        	StringTokenizer tokenizer = new StringTokenizer(linea, ";");
		        	
		        	// Cargar secuencialmente
		        	isbn = tokenizer.nextToken();
		        	titulo = tokenizer.nextToken();
		        	autor = tokenizer.nextToken();
		        	genero = tokenizer.nextToken();
		        	anio = Integer.parseInt(tokenizer.nextToken());
		        	ejemplares = Integer.parseInt(tokenizer.nextToken());
		        	
		        	// Una vez cargados todos los datos, instanciar objeto y cargar en mapa.
		        	libros.put(isbn, new Libro(isbn, titulo, autor, genero, anio, ejemplares));
        	}
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
      
        return libros;
    }

    /**
     * Carga los socios desde un archivo de texto.
     * Formato de cada línea: nroSocio;nombre;apellido;email;activo
     * Ejemplo: S001;Juan;Perez;juan@mail.com;true
     *
     * @return mapa indexado por nroSocio
     */
    public static ProbeHashMap<String, Socio> cargarSocios(String fileName)
            throws FileNotFoundException {

        ProbeHashMap<String, Socio> socios = new ProbeHashMap<>();
        
        String caracterNroSocio = "S";
        File archivo = new File(fileName);
        
        String linea = "";
        int nroSocio = 0;
        String nombre = "";
        String apellido = "";
        String email = "";
        boolean activo = true;
        
        try(Scanner scanner = new Scanner(archivo))
        {
        	while(scanner.hasNextLine())   // Leer linea por linea el archivo entero.
        	{
		        	linea = scanner.nextLine();
		        	StringTokenizer tokenizer = new StringTokenizer(linea, ";");
		        	
		        	// Borrar la S al inicio.
		        	String stringSocio = tokenizer.nextToken();
		        	stringSocio = stringSocio.replace(caracterNroSocio, "");
		        	
		        	nroSocio = Integer.parseInt(stringSocio);
		        	
		        	nombre = tokenizer.nextToken();
		        	apellido = tokenizer.nextToken();
		        	email = tokenizer.nextToken();
		        	activo = Boolean.parseBoolean(tokenizer.nextToken());
		        	
		        	// el cast mas horrible, pecado de la informatica
		        	socios.put("" + nroSocio, new Socio(nroSocio, nombre, apellido, email, activo));
        	}
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        
        return socios;
    }

    /**
     * Carga los préstamos activos desde un archivo de texto.
     * Formato de cada línea: nroSocio;isbn;fechaPrestamo;fechaVencimiento
     * Ejemplo: S001;978-0;01/06/2026;15/06/2026
     *
     * @return mapa indexado por nroSocio con la lista de préstamos de cada socio
     */
    public static ProbeHashMap<String, LinkedPositionalList<Prestamo>> cargarPrestamos(
            String fileName,
            ProbeHashMap<String, Socio> socios,
            ProbeHashMap<String, Libro> libros)
            throws FileNotFoundException {

	        ProbeHashMap<String, LinkedPositionalList<Prestamo>> prestamos = new ProbeHashMap<>();
	        
	        File archivo = new File(fileName);
	        
	        try(Scanner scanner = new Scanner(archivo))
	        {
	        	String linea = "";
	        	String nroSocio = "";
	        	String isbn = "";
	        	LocalDate fechaPrestamo = null;
	        	LocalDate fechaVencimiento = null;
	        	int dia = 0;
	        	int mes = 0;
	        	int anio = 0;
	        	
	        	while(scanner.hasNextLine())   // Leer linea por linea el archivo entero.
	        	{
			        	linea = scanner.nextLine();
			        	StringTokenizer tokenizer_dato = new StringTokenizer(linea, ";");
			        	nroSocio = tokenizer_dato.nextToken();
			        	isbn = tokenizer_dato.nextToken();
			        	
			        	// Tokenizar fecha con '/'
			        	StringTokenizer tokenizer_fecha = new StringTokenizer(tokenizer_dato.nextToken(), "/");
			        	dia = Integer.parseInt(tokenizer_fecha.nextToken());
			        	mes = Integer.parseInt(tokenizer_fecha.nextToken());
			        	anio = Integer.parseInt(tokenizer_fecha.nextToken());
			        	fechaPrestamo = LocalDate.of(anio, mes, dia);
			        	
			        	tokenizer_fecha = new StringTokenizer(tokenizer_dato.nextToken(), "/");
			        	dia = Integer.parseInt(tokenizer_fecha.nextToken());
			        	mes = Integer.parseInt(tokenizer_fecha.nextToken());
			        	anio = Integer.parseInt(tokenizer_fecha.nextToken());
			        	fechaVencimiento = LocalDate.of(anio, mes, dia);
			        	
			        	Socio getSocio = socios.get(nroSocio);
			        	Libro getLibro = libros.get(isbn);
			        	
			        	// Una vez cargado el prestamo, cargarlo en la lista del socio correspondiente.
			        	Prestamo carga = new Prestamo(getSocio, getLibro, fechaPrestamo, fechaVencimiento);
			        	
			        	
	        	}
	        }
	        catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        
	        return prestamos;
    }
}
