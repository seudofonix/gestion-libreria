package biblioteca.modelo;

import java.util.Objects;

public class Libro {

    private String isbn;
    private String titulo;
    private String autor;
    private String genero;
    private int anioPublicacion;
    private int ejemplaresDisponibles;

    public Libro(String isbn, String titulo, String autor, String genero,
                 int anioPublicacion, int ejemplaresDisponibles) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anioPublicacion = anioPublicacion;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

	@Override
	public String toString() {
		return "Libro: \nISBN= " + isbn + "\nTitulo= " + titulo + "\nAutor= " + autor + "\nGenero= " + genero
				+ "\nAnio de publicacion= " + anioPublicacion + "\nEjemplares disponibles= " + ejemplaresDisponibles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anioPublicacion, autor, ejemplaresDisponibles, genero, isbn, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return anioPublicacion == other.anioPublicacion && Objects.equals(autor, other.autor)
				&& ejemplaresDisponibles == other.ejemplaresDisponibles && Objects.equals(genero, other.genero)
				&& Objects.equals(isbn, other.isbn) && Objects.equals(titulo, other.titulo);
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getAnioPublicacion() {
		return anioPublicacion;
	}

	public void setAnioPublicacion(int anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public int getEjemplaresDisponibles() {
		return ejemplaresDisponibles;
	}

	public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
		this.ejemplaresDisponibles = ejemplaresDisponibles;
	}

	
}
