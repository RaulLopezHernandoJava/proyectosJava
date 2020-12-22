package com.juegodemesa.modelos;

public class ComunidadAutonoma {
	private Long id;
	private String nombre;
	
	
	// Constructores
	
	public ComunidadAutonoma(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	
	public ComunidadAutonoma() {
		
	}

	// Getters and Setters
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	// HashCode() and Equals()
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComunidadAutonoma other = (ComunidadAutonoma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}


	// toString()
	
	@Override
	public String toString() {
		return "ComunidadAutonoma [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
