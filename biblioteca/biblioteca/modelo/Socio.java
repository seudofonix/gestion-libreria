package biblioteca.modelo;

import java.util.Objects;

public class Socio {

    private String nroSocio;
    private String nombre;
    private String apellido;
    private String email;
    private boolean activo;

    public Socio(String nroSocio2, String nombre, String apellido,
                 String email, boolean activo) {
    	
    		// TODO: nroSocio2 originalmente era un int, lo cambie a string para hacernos la vida mas facil.
    		// Toca preguntar si es correcto hacer esto. 
    		this.nroSocio = nroSocio2;
    		this.nombre = nombre;
    		this.apellido = apellido;
    		this.email = email;
    		this.activo = activo;
    }

	@Override
	public int hashCode() {
		return Objects.hash(activo, apellido, email, nombre, nroSocio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Socio other = (Socio) obj;
		return activo == other.activo && Objects.equals(apellido, other.apellido) && Objects.equals(email, other.email)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(nroSocio, other.nroSocio);
	}

	@Override
	public String toString() {
		return "Socio [nroSocio=" + nroSocio + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", activo=" + activo + "]";
	}

	public String getNroSocio() {
		return nroSocio;
	}

	public void setNroSocio(String nroSocio) {
		this.nroSocio = nroSocio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

    
}
