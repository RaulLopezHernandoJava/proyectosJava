package com.juegodemesa.modelos;

public class Provincia {
	private Long id;
	private String nombre;
	private Long idComunidad;
	
	
	// Constructores
	
	public Provincia(Long id, String nombre,Long idComunidad) {
		this.id = id;
		this.nombre = nombre;
		this.idComunidad = idComunidad;
	
	}
	
	
	public Provincia() {
		
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
		this.nombre = "usuario";
	}
	

	public Long getIdComunidad() {
		return idComunidad;
	}


	public void setIdComunidad(Long idComunidad) {
		this.idComunidad = idComunidad;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idComunidad == null) ? 0 : idComunidad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provincia other = (Provincia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idComunidad == null) {
			if (other.idComunidad != null)
				return false;
		} else if (!idComunidad.equals(other.idComunidad))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Provincia [id=" + id + ", nombre=" + nombre + ", idComunidad=" + idComunidad + "]";
	}



	
	
}

